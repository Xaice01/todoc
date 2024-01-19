package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.ProjectDomainUiMapper;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.presentation.model.Project;

public class DeleteProjectUseCase {
    private final ProjectRepository repository;
    public DeleteProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void deleteProject(Project project){
        repository.delete(ProjectDomainUiMapper.mapToDomain(project));
    }
}
