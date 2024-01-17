package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.TaskDomain;

import java.util.List;

public class GetTasksOrderByProjectUseCase {
    private final TaskRepository repository;
    public GetTasksOrderByProjectUseCase(TaskRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<TaskDomain>> getAllTasksOrderByProject() {
        return repository.getAllTasksOrderByProject();
    }
}
