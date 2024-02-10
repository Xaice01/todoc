package com.cleanup.todoc.presentation.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cleanup.todoc.R
import com.cleanup.todoc.presentation.model.Project
import com.cleanup.todoc.presentation.model.Task
import com.cleanup.todoc.presentation.model.Task.TaskAZComparator
import com.cleanup.todoc.presentation.model.Task.TaskOldComparator
import com.cleanup.todoc.presentation.model.Task.TaskRecentComparator
import com.cleanup.todoc.presentation.model.Task.TaskZAComparator
import com.cleanup.todoc.presentation.ui.TasksAdapter.DeleteTaskListener
import com.cleanup.todoc.presentation.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Collections
import java.util.Date
import java.util.logging.Logger

/**
 *
 * Home activity of the application which is displayed when the user opens the app.
 *
 * Displays the list of tasks.
 *
 * @author Gaëtan HERFRAY and Xavier Carpentier
 */
class MainActivity : AppCompatActivity(), DeleteTaskListener {
    /*
     * create the Viewmodel
     */
    private val taskViewModel: TaskViewModel by viewModels()

    /**
     * List of all projects available in the application
     */
    private var allProjects: List<Project> = ArrayList()

    /**
     * List of all current tasks of the application
     */
    private var tasks: List<Task> = ArrayList()

    /**
     * The adapter which handles the list of tasks
     */
    private lateinit var adapter: TasksAdapter

    /**
     * The sort method to be used to display tasks
     */
    private var sortMethod = SortMethod.NONE

    /**
     * EditText that allows user to set the name of a task
     */
    private var dialogEditText: EditText? = null

    /**
     * Spinner that allows the user to associate a project to a task
     */
    private var dialogSpinner: Spinner? = null

    /**
     * The RecyclerView which displays the list of tasks
     */
    // Suppress warning is safe because variable is initialized in onCreate
    private lateinit var listTasks: RecyclerView

    /**
     * The TextView displaying the empty state
     */
    // Suppress warning is safe because variable is initialized in onCreate
    private lateinit var lblNoTasks: TextView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                taskViewModel.insertBaseData()
            }
        }
        lifecycleScope.launch {
            taskViewModel.allProjects.collectLatest { list -> allProjects = list }
        }

        lifecycleScope.launch {
            taskViewModel.allTasks.collectLatest { list ->
                Log.d("MainActivity", "tasks: $list")
                tasks = list
                updateTasks()}
        }

        adapter = TasksAdapter((allProjects), tasks, this)
        setContentView(R.layout.activity_main)
        listTasks = findViewById(R.id.list_tasks)
        lblNoTasks = findViewById(R.id.lbl_no_task)
        listTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listTasks.adapter = adapter
        findViewById<View>(R.id.fab_add_task).setOnClickListener(View.OnClickListener { showAddTaskDialog() })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_alphabetical -> {
                sortMethod = SortMethod.ALPHABETICAL
            }

            R.id.filter_alphabetical_inverted -> {
                sortMethod = SortMethod.ALPHABETICAL_INVERTED
            }

            R.id.filter_oldest_first -> {
                sortMethod = SortMethod.OLD_FIRST
            }

            R.id.filter_recent_first -> {
                sortMethod = SortMethod.RECENT_FIRST
            }
        }
        updateTasks()
        return super.onOptionsItemSelected(item)
    }

    override fun onDeleteTask(task: Task) {
        taskViewModel.deleteTask(task)
    }

    /**
     * Called when the user clicks on the positive button of the Create Task Dialog.
     *
     * @param dialogInterface the current displayed dialog
     */
    private fun onPositiveButtonClick(dialogInterface: DialogInterface) {
        // If dialog is open
        Log.d("MainActivity", "onPositiveButtonClick: ")
        if (dialogEditText != null && dialogSpinner != null) {
            // Get the name of the task
            val taskName = dialogEditText!!.text.toString()

            // Get the selected project to be associated to the task
            var taskProject: Project? = null
            if (dialogSpinner!!.selectedItem is Project) {
                taskProject = dialogSpinner!!.selectedItem as Project
            }

            // If a name has not been set
            if (taskName.trim { it <= ' ' }.isEmpty()) {
                dialogEditText!!.error = getString(R.string.empty_task_name)
            } else if (taskProject != null) {
                // the logique data generate id
                val id: Long = 0
                val task = Task(
                    id,
                    taskProject.id,
                    taskName,
                    Date().time
                )
                addTask(task)
                dialogInterface.dismiss()
            } else {
                dialogInterface.dismiss()
            }
        } else {
            dialogInterface.dismiss()
        }
    }

    /**
     * Shows the Dialog for adding a Task
     */
    private fun showAddTaskDialog() {
        val dialog = createAddTaskDialog()
        dialog.show()
        dialogEditText = dialog.findViewById(R.id.txt_task_name)
        dialogSpinner = dialog.findViewById(R.id.project_spinner)
        populateDialogSpinner()
    }

    /**
     * Adds the given task to the list of created tasks.
     *
     * @param task the task to be added to the list
     */
    private fun addTask(task: Task) {
        Log.d("MainActivity", "addTask: ")
        taskViewModel.createTask(task)
    }

    /**
     * Updates the list of tasks in the UI
     */
    private fun updateTasks() {
        if (tasks.isEmpty()) {
            lblNoTasks.visibility = View.VISIBLE
            listTasks.visibility = View.GONE
        } else {
            lblNoTasks.visibility = View.GONE
            listTasks.visibility = View.VISIBLE
            when (sortMethod) {
                SortMethod.ALPHABETICAL -> Collections.sort(tasks, TaskAZComparator())
                SortMethod.ALPHABETICAL_INVERTED -> Collections.sort(tasks, TaskZAComparator())
                SortMethod.RECENT_FIRST -> Collections.sort(tasks, TaskRecentComparator())
                SortMethod.OLD_FIRST -> Collections.sort(tasks, TaskOldComparator())
                SortMethod.NONE -> Log.d("MainActivity", "updateTasks: No sort method")
            }
            adapter.updateTasks((allProjects), tasks)
        }
    }

    private fun createAddTaskDialog(): AlertDialog {
        val alertBuilder = AlertDialog.Builder(this, R.style.Dialog)
        alertBuilder.setTitle(R.string.add_task)
        alertBuilder.setView(R.layout.dialog_add_task)
        alertBuilder.setPositiveButton(R.string.add, null)
        alertBuilder.setOnDismissListener {
            // Reset dialog-related properties on dismiss
            dialogEditText = null
            dialogSpinner = null
            /*dialog.dismiss()*/
        }
        val dialog = alertBuilder.create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                // Safely call 'onPositiveButtonClick' with 'dialog' as it is non-null here.
                onPositiveButtonClick(dialog)
            }
        }

        return dialog
    }

    /**
     * Sets the data of the Spinner with projects to associate to a new task
     */
    private fun populateDialogSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allProjects)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (dialogSpinner != null) {
            dialogSpinner!!.adapter = adapter
        }
    }

    /**
     * List of all possible sort methods for task
     */
    private enum class SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,

        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,

        /**
         * Lastly created first
         */
        RECENT_FIRST,

        /**
         * First created first
         */
        OLD_FIRST,

        /**
         * No sort
         */
        NONE
    }
}
