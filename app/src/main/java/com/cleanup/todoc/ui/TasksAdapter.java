package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;

import java.util.List;

/**
 * <p>Adapter which handles the list of tasks to display in the dedicated RecyclerView.</p>
 *
 * @author Gaëtan HERFRAY
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {
    /**
     * The list of tasks the adapter deals with
     */
    @NonNull
    private List<TaskDomain> taskDomains;

    private List<ProjectDomain> projectDomains;

    /**
     * The listener for when a task needs to be deleted
     */
    @NonNull
    private final DeleteTaskListener deleteTaskListener;

    /**
     * Instantiates a new TasksAdapter.
     *
     * @param taskDomains the list of tasks the adapter deals with to set
     */
    TasksAdapter(@NonNull final List<ProjectDomain> projectDomains, @NonNull final List<TaskDomain> taskDomains, @NonNull final DeleteTaskListener deleteTaskListener) {
        this.projectDomains = projectDomains;
        this.taskDomains = taskDomains;
        this.deleteTaskListener = deleteTaskListener;
    }

    /**
     * Updates the list of tasks the adapter deals with.
     *
     * @param taskDomains the list of tasks the adapter deals with to set
     */
    void updateTasks(@NonNull final List<ProjectDomain> projectsDomain, @NonNull final List<TaskDomain> taskDomains) {
        this.projectDomains = projectsDomain;
        this.taskDomains = taskDomains;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        return new TaskViewHolder(view, deleteTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        taskViewHolder.bind(taskDomains.get(position));
    }

    @Override
    public int getItemCount() {
        return taskDomains.size();
    }

    /**
     * Listener for deleting tasks
     */
    public interface DeleteTaskListener {
        /**
         * Called when a task needs to be deleted.
         *
         * @param taskDomain the task that needs to be deleted
         */
        void onDeleteTask(TaskDomain taskDomain);
    }

    /**
     * <p>ViewHolder for task items in the tasks list</p>
     *
     * @author Gaëtan HERFRAY
     */
    class TaskViewHolder extends RecyclerView.ViewHolder {
        /**
         * The circle icon showing the color of the project
         */
        private final AppCompatImageView imgProject;

        /**
         * The TextView displaying the name of the task
         */
        private final TextView lblTaskName;

        /**
         * The TextView displaying the name of the project
         */
        private final TextView lblProjectName;

        /**
         * The delete icon
         */
        private final AppCompatImageView imgDelete;

        /**
         * The listener for when a task needs to be deleted
         */
        private final DeleteTaskListener deleteTaskListener;

        /**
         * Instantiates a new TaskViewHolder.
         *
         * @param itemView the view of the task item
         * @param deleteTaskListener the listener for when a task needs to be deleted to set
         */
        TaskViewHolder(@NonNull View itemView, @NonNull DeleteTaskListener deleteTaskListener) {
            super(itemView);

            this.deleteTaskListener = deleteTaskListener;

            imgProject = itemView.findViewById(R.id.img_project);
            lblTaskName = itemView.findViewById(R.id.lbl_task_name);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Object tag = view.getTag();
                    if (tag instanceof TaskDomain) {
                        TaskViewHolder.this.deleteTaskListener.onDeleteTask((TaskDomain) tag);
                    }
                }
            });
        }

        /**
         * Binds a task to the item view.
         *
         * @param taskDomain the task to bind in the item view
         */
        void bind(TaskDomain taskDomain) {
            lblTaskName.setText(taskDomain.getName());
            imgDelete.setTag(taskDomain);

            final ProjectDomain taskProjectDomain = taskDomain.getProject(projectDomains);
            if (taskProjectDomain != null) {
                imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProjectDomain.getColor()));
                lblProjectName.setText(taskProjectDomain.getName());
            } else {
                imgProject.setVisibility(View.INVISIBLE);
                lblProjectName.setText("");
            }

        }
    }
}
