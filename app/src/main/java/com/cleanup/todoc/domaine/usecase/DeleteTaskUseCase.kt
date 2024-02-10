package com.cleanup.todoc.domaine.usecase

import com.cleanup.todoc.data.repository.TaskRepository
import com.cleanup.todoc.domaine.TaskDomainUiMapper
import com.cleanup.todoc.presentation.model.Task

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.delete(TaskDomainUiMapper.mapToDomain(task))
    }
}
