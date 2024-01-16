package com.cleanup.todoc.model.usecase;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.repository.TaskRepository;

import java.util.List;

public class GetTasksOrderByProjectUseCase {
    private final TaskRepository repository;
    public GetTasksOrderByProjectUseCase(TaskRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<Task>> getAllTasksOrderByProject() {
        return repository.getAllTasksOrderByProject();
    }
}
