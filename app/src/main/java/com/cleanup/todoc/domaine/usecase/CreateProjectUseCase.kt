package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.domaine.ProjectDomainUiMapper;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.presentation.model.Project;

public class CreateProjectUseCase {
    private final ProjectRepository repository;
    public CreateProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void createProject(Project project){
        repository.insert(ProjectDomainUiMapper.mapToDomain(project));
    }
}
