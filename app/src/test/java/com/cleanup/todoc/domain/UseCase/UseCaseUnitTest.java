package com.cleanup.todoc.domain.UseCase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.domaine.usecase.CreateProjectUseCase;
import com.cleanup.todoc.domaine.usecase.CreateTaskUseCase;
import com.cleanup.todoc.domaine.usecase.DeleteProjectUseCase;
import com.cleanup.todoc.domaine.usecase.DeleteTaskUseCase;
import com.cleanup.todoc.domaine.usecase.GetProjectsUseCase;
import com.cleanup.todoc.domaine.usecase.GetTasksOrderByProjectUseCase;
import com.cleanup.todoc.domaine.usecase.GetTasksUseCase;
import com.cleanup.todoc.presentation.model.Project;
import com.cleanup.todoc.presentation.model.Task;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseUnitTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    private CreateProjectUseCase createProjectUseCase;
    private DeleteProjectUseCase deleteProjectUseCase;
    private CreateTaskUseCase createTaskUseCase;
    private DeleteTaskUseCase deleteTaskUseCase;
    private GetProjectsUseCase getProjectsUseCase;
    private GetTasksOrderByProjectUseCase getTasksOrderByProjectUseCase;
    private GetTasksUseCase getTasksUseCase;

    @Before
    public void setup(){
        createProjectUseCase = new CreateProjectUseCase(projectRepository);
        deleteProjectUseCase = new DeleteProjectUseCase(projectRepository);
        createTaskUseCase = new CreateTaskUseCase(taskRepository);
        deleteTaskUseCase = new DeleteTaskUseCase(taskRepository);
        getProjectsUseCase = new GetProjectsUseCase(projectRepository);
        getTasksOrderByProjectUseCase = new GetTasksOrderByProjectUseCase(taskRepository);
        getTasksUseCase = new GetTasksUseCase(taskRepository);

    }

    @Test
    public void testCreateProjectUseCase(){
        //Given
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);

        //when
        createProjectUseCase.createProject(project);

        //Then
        verify(projectRepository,times(1)).insert(any());
    }

    @Test
    public void testDeleteProjectUseCase(){
        //Given
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        createProjectUseCase.createProject(project);

        //when
        deleteProjectUseCase.deleteProject(project);

        //Then
        verify(projectRepository,times(1)).delete(any());
    }

    @Test
    public void testCreateTaskUseCase(){
        //Given
        long creationTimestamp = new Date().getTime();
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        Task task = new Task(1, 1L, "testTask",creationTimestamp);

        //when
        createTaskUseCase.createTask(task);

        //Then
        verify(taskRepository,times(1)).insert(any());
    }
    @Test
    public void testDeleteTaskUseCase(){
        //Given
        long creationTimestamp = new Date().getTime();
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        Task task = new Task(1, 1L, "testTask",creationTimestamp);
        createTaskUseCase.createTask(task);

        //when
        deleteTaskUseCase.deleteTask(task);

        //Then
        verify(taskRepository,times(1)).delete(any());
    }

    @Test
    public void testGetProjectUseCase(){
        //Given
        List<ProjectDomain> projects = new ArrayList<>();
        projects.add(new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1));
        projects.add(new ProjectDomain(2L, "Projet Lucidia", 0xFFB4CDBA));
        projects.add(new ProjectDomain(3L, "Projet Circus", 0xFFA3CED2));
        MutableLiveData<List<ProjectDomain>> projectLiveData = new MutableLiveData<>();
        projectLiveData.setValue(projects);

        //When
        when(projectRepository.allProjects).thenReturn(projectLiveData);
        LiveData<List<Project>> listProject = getProjectsUseCase.getAllProjects();

        //Then
        assertNotNull(listProject);
    }

    @Test
    public void testGetTaskOrderByProjectUseCase(){
        //Given
        List<ProjectDomain> projects = new ArrayList<>();
        projects.add(new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1));
        projects.add(new ProjectDomain(2L, "Projet Lucidia", 0xFFB4CDBA));
        projects.add(new ProjectDomain(3L, "Projet Circus", 0xFFA3CED2));

        long creationTimestamp1 = new Date().getTime();
        long creationTimestamp2 = new Date().getTime();
        long creationTimestamp3 = new Date().getTime();

        List<TaskDomain> taskDomains = new ArrayList<TaskDomain>();
        taskDomains.add(new TaskDomain(1,1,"TestTask1",creationTimestamp1));
        taskDomains.add(new TaskDomain(2,1,"TestTask2",creationTimestamp2));
        taskDomains.add(new TaskDomain(3,2,"TestTask3",creationTimestamp3));
        MutableLiveData<List<TaskDomain>> taskLiveData = new MutableLiveData<>();
        taskLiveData.setValue(taskDomains);

        //When
        when(taskRepository.getAllTasksOrderByProject()).thenReturn(taskLiveData);
        LiveData<List<Task>> listTask = getTasksOrderByProjectUseCase.getAllTasksOrderByProject();

        //Then
        assertNotNull(listTask);
    }

    @Test
    public void testGetTasksUseCase(){
        //Given
        List<ProjectDomain> projects = new ArrayList<>();
        projects.add(new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1));
        projects.add(new ProjectDomain(2L, "Projet Lucidia", 0xFFB4CDBA));
        projects.add(new ProjectDomain(3L, "Projet Circus", 0xFFA3CED2));

        long creationTimestamp1 = new Date().getTime();
        long creationTimestamp2 = new Date().getTime();
        long creationTimestamp3 = new Date().getTime();

        List<TaskDomain> taskDomains = new ArrayList<TaskDomain>();
        taskDomains.add(new TaskDomain(1,1,"TestTask1",creationTimestamp1));
        taskDomains.add(new TaskDomain(2,1,"TestTask2",creationTimestamp2));
        taskDomains.add(new TaskDomain(3,2,"TestTask3",creationTimestamp3));
        MutableLiveData<List<TaskDomain>> taskLiveData = new MutableLiveData<>();
        taskLiveData.setValue(taskDomains);

        //When
        when(taskRepository.getAllTasks()).thenReturn(taskLiveData);
        LiveData<List<Task>> listTask = getTasksUseCase.getAllTasks();

        //Then
        assertNotNull(listTask);
    }
}
