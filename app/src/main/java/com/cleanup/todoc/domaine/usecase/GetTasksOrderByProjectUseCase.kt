package com.cleanup.todoc.domaine.usecase

import com.cleanup.todoc.data.repository.TaskRepository
import com.cleanup.todoc.domaine.TaskDomainUiMapper
import com.cleanup.todoc.domaine.model.TaskDomain
import com.cleanup.todoc.presentation.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasksOrderByProjectUseCase(private val repository: TaskRepository) {
    val allTasksOrderByProject: Flow<List<Task>>
        get() = repository.allTasksOrderByProject.map { tasks: List<TaskDomain> ->
            TaskDomainUiMapper.mapToTaskList(
                tasks
            )
        }
}