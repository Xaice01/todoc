package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.TaskDomain;

public class DeleteTaskUseCase {
    private final TaskRepository repository;
    public DeleteTaskUseCase(TaskRepository repository){this.repository=repository;}
    public void deleteTask(TaskDomain taskDomain){
        repository.delete(taskDomain);

}
}
