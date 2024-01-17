package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.TaskDomain;

public class CreateTaskUseCase {
    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository){this.repository=repository;}

    public void createTask(TaskDomain taskDomain){
        repository.insert(taskDomain);
    }
}
