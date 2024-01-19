package com.cleanup.todoc.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.domaine.usecase.CreateTaskUseCase;
import com.cleanup.todoc.domaine.usecase.DeleteTaskUseCase;
import com.cleanup.todoc.domaine.usecase.GetProjectsUseCase;
import com.cleanup.todoc.domaine.usecase.GetTasksUseCase;
import com.cleanup.todoc.presentation.model.Project;
import com.cleanup.todoc.presentation.model.Task;

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
