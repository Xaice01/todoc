package com.cleanup.todoc.model.usecase;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.repository.TaskRepository;

import java.util.List;

public class GetTasksUseCase {
    private final TaskRepository repository;
    public GetTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<Task>> getAllTasks() {
        return repository.getAllTasks();
    }
}