package com.cleanup.todoc.model.usecase;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.repository.TaskRepository;

public class DeleteTaskUseCase {
    private final TaskRepository repository;
    public DeleteTaskUseCase(TaskRepository repository){this.repository=repository;}
    public void deleteTask(Task task){
        repository.delete(task);

}
}
