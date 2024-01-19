package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.data.repository.TaskRepository;

public class GetNewTaskIdUseCase {

    private final TaskRepository repository;

    public GetNewTaskIdUseCase(TaskRepository repository){this.repository=repository;}

    public long GetNewTaskId(){
        return 1;
    }
}
