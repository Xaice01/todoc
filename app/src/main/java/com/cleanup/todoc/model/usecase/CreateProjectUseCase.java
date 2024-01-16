package com.cleanup.todoc.model.usecase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.repository.ProjectRepository;

public class CreateProjectUseCase {
    private final ProjectRepository repository;
    public CreateProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void createProject(Project project){
        repository.insert(project);
    }
}
