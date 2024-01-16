package com.cleanup.todoc.model.usecase;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.repository.TaskRepository;

public class CreateTaskUseCase {
    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository){this.repository=repository;}

    public void createTask(Task task){
        repository.insert(task);
    }
}
