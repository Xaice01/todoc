package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.TaskDomainUiMapper;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Task;

import java.util.List;

public class GetTasksOrderByProjectUseCase {
    private final TaskRepository repository;
    public GetTasksOrderByProjectUseCase(TaskRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<Task>> getAllTasksOrderByProject() {
        return Transformations.map(repository.getAllTasksOrderByProject(), TaskDomainUiMapper::mapToTaskList);
    }
}
