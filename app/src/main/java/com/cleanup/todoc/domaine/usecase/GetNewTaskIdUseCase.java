package com.cleanup.todoc.domaine.usecase;

import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.TaskDomain;

import java.util.List;

public class GetNewTaskIdUseCase {

    private final TaskRepository repository;

    public GetNewTaskIdUseCase(TaskRepository repository){this.repository=repository;}

    public long GetNewTaskId(){
        List<TaskDomain> listTask= repository.getAllTasks().getValue();
        long idToReturn = 0;
        if(listTask.isEmpty()){
            return idToReturn;
        }else{
            idToReturn=listTask.size();
        }
        return idToReturn;
    }
}
