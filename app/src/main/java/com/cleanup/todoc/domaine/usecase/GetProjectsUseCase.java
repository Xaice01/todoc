package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.datasource.model.ProjectEntity;
import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;

import java.util.List;

public class GetProjectsUseCase {
    private final ProjectRepository repository;

    public GetProjectsUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ProjectDomain>> getAllProjects() {
        return repository.getAllProjects();

    }
}
