package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.TaskDomainUiMapper;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Task;

public class CreateTaskUseCase {
    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository){this.repository=repository;}

    public void createTask(Task task){
        repository.insert(TaskDomainUiMapper.mapToDomain(task));
    }
}
