package com.cleanup.todoc.model.usecase;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.repository.ProjectRepository;

import java.util.List;

public class GetProjectsUseCase {
    private final ProjectRepository repository;

    public GetProjectsUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Project>> getAllProjects() {
        return repository.getAllProjects();

    }
}
