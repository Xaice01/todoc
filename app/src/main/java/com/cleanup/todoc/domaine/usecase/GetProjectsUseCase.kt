package com.cleanup.todoc.domaine.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.ProjectDomainUiMapper;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.presentation.model.Project;

import java.util.List;

public class GetProjectsUseCase {
    private final ProjectRepository repository;

    public GetProjectsUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Project>> getAllProjects() {
        return Transformations.map(repository.getAllProjects(), ProjectDomainUiMapper::mapToProjectList);

    }
}
