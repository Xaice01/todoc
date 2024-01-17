package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.datasource.model.ProjectEntity;
import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;

public class DeleteProjectUseCase {
    private final ProjectRepository repository;
    public DeleteProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void deleteProject(ProjectDomain projectDomain){
        repository.delete(projectDomain);
    }
}
