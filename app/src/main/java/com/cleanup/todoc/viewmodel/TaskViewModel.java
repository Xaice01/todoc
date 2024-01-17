package com.cleanup.todoc.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.repository.ProjectRepository;
import com.cleanup.todoc.model.repository.TaskRepository;
import com.cleanup.todoc.model.usecase.CreateTaskUseCase;
import com.cleanup.todoc.model.usecase.DeleteTaskUseCase;
import com.cleanup.todoc.model.usecase.GetProjectsUseCase;
import com.cleanup.todoc.model.usecase.GetTasksUseCase;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    //----------------------------------------------------
    //Data
    //----------------------------------------------------
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Project>> allProjects;

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

    public LiveData<List<Task>> getAllTasks(){ return allTasks;}

    public LiveData<List<Project>> getAllProjects(){return allProjects;}

    public void createTask(Task task){createTaskUseCase.createTask(task);}

    public void deleteTask(Task task){deleteTaskUseCase.deleteTask(task);}

}
