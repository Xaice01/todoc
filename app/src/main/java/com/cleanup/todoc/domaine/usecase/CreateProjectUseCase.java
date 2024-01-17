package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.datasource.model.ProjectEntity;
import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;

public class CreateProjectUseCase {
    private final ProjectRepository repository;
    public CreateProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void createProject(ProjectDomain projectDomain){
        repository.insert(projectDomain);
    }
}
