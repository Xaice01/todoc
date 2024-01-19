package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.TaskDomainUiMapper;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Task;

public class DeleteTaskUseCase {
    private final TaskRepository repository;
    public DeleteTaskUseCase(TaskRepository repository){this.repository=repository;}
    public void deleteTask(Task task){
        repository.delete(TaskDomainUiMapper.mapToDomain(task));
    }
}
