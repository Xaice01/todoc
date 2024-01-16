package com.cleanup.todoc.model.usecase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.repository.ProjectRepository;

public class DeleteProjectUseCase {
    private final ProjectRepository repository;
    public DeleteProjectUseCase(ProjectRepository repository){this.repository=repository;}
    public void deleteProject(Project project){
        repository.delete(project);
    }
}
