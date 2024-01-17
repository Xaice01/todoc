package com.cleanup.todoc.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.domaine.usecase.CreateTaskUseCase;
import com.cleanup.todoc.domaine.usecase.DeleteTaskUseCase;
import com.cleanup.todoc.domaine.usecase.GetProjectsUseCase;
import com.cleanup.todoc.domaine.usecase.GetTasksUseCase;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    //----------------------------------------------------
    //Data
    //----------------------------------------------------
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private LiveData<List<TaskDomain>> allTasks;
    private LiveData<List<ProjectDomain>> allProjects;

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    private GetTasksUseCase getTasksUseCase;
    private GetProjectsUseCase getProjectsUseCase;
    private CreateTaskUseCase createTaskUseCase;
    private DeleteTaskUseCase deleteTaskUseCase;


    //----------------------------------------------------
    //Constructor
    //----------------------------------------------------
    public TaskViewModel(Application application){
        super(application);
        taskRepository = new TaskRepository(application);
        projectRepository = new ProjectRepository(application);

        getTasksUseCase = new GetTasksUseCase(taskRepository);
        getProjectsUseCase = new GetProjectsUseCase(projectRepository);
        createTaskUseCase = new CreateTaskUseCase(taskRepository);
        deleteTaskUseCase = new DeleteTaskUseCase(taskRepository);

        allTasks = getTasksUseCase.getAllTasks();
        allProjects = getProjectsUseCase.getAllProjects();
    }

    public LiveData<List<TaskDomain>> getAllTasks(){ return allTasks;}

    public LiveData<List<ProjectDomain>> getAllProjects(){return allProjects;}

    public void createTask(TaskDomain taskDomain){createTaskUseCase.createTask(taskDomain);}

    public void deleteTask(TaskDomain taskDomain){deleteTaskUseCase.deleteTask(taskDomain);}

}
