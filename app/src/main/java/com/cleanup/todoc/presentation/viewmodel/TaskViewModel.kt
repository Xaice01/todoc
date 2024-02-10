package com.cleanup.todoc.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanup.todoc.data.repository.ProjectRepository
import com.cleanup.todoc.data.repository.TaskRepository
import com.cleanup.todoc.domaine.model.ProjectDomain
import com.cleanup.todoc.domaine.usecase.CreateTaskUseCase
import com.cleanup.todoc.domaine.usecase.DeleteTaskUseCase
import com.cleanup.todoc.domaine.usecase.GetProjectsUseCase
import com.cleanup.todoc.domaine.usecase.GetTasksUseCase
import com.cleanup.todoc.presentation.model.Project
import com.cleanup.todoc.presentation.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *
 * ViewModel for MainActivity.
 *
 * @author Xavier Carpentier
 */
class TaskViewModel(application: Application) : AndroidViewModel(application) {
    //----------------------------------------------------
    //Data
    //----------------------------------------------------
    private val taskRepository: TaskRepository
    private val projectRepository: ProjectRepository

    @JvmField
    val allTasks: Flow<List<Task>>

    @JvmField
    val allProjects: Flow<List<Project>>

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private val getTasksUseCase: GetTasksUseCase
    private val getProjectsUseCase: GetProjectsUseCase
    private val createTaskUseCase: CreateTaskUseCase
    private val deleteTaskUseCase: DeleteTaskUseCase

    //----------------------------------------------------
    //Constructor
    //----------------------------------------------------
    init {
        taskRepository = TaskRepository(application)
        projectRepository = ProjectRepository(application)
        getTasksUseCase = GetTasksUseCase(taskRepository)
        getProjectsUseCase = GetProjectsUseCase(projectRepository)
        createTaskUseCase = CreateTaskUseCase(taskRepository)
        deleteTaskUseCase = DeleteTaskUseCase(taskRepository)
        allTasks = getTasksUseCase.allTasks
        allProjects = getProjectsUseCase.allProjects
    }

    fun createTask(task: Task) {
        viewModelScope.launch { createTaskUseCase(task) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { deleteTaskUseCase(task) }
    }

    fun insertBaseData() {
        viewModelScope.launch {
            projectRepository.insert(ProjectDomain(0, "Projet Tartampion", 0xFFEADAD1.toInt()))
            projectRepository.insert(ProjectDomain(1, "Projet Lucidia", 0xFFB4CDBA.toInt()))
        }
    }
}
