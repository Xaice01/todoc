package com.cleanup.todoc.presentation;

import static org.junit.Assert.assertEquals;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.repository.ProjectRepository;
import com.cleanup.todoc.data.repository.TaskRepository;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Project;
import com.cleanup.todoc.presentation.model.Task;
import com.cleanup.todoc.presentation.viewmodel.TaskViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskViewModelUnitTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application application;

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private TaskViewModel taskViewModel;

    @Before
    public void setup(){
        taskRepository = new TaskRepository(application);
        projectRepository = new ProjectRepository(application);

        // Initialize your ViewModel with the mock repository
        taskViewModel = new TaskViewModel(application);
    }

    @Test
    public void testgetAllProjects() {
        // Create a project for testing
        projectRepository.insert(new ProjectDomain(0, "Project 1", 0xFFA3CED2));


        // Call the getAllProjects method
        LiveData<List<Project>> allProjects = taskViewModel.getAllProjects();

        // Assert taskViewModel projects is equal at repository projects
        assertEquals(allProjects.getValue(),projectRepository.getAllProjects().getValue());
    }

    @Test
    public void testgetAllTasks() {
        // Create a project for testing
        projectRepository.insert(new ProjectDomain(0, "Project 1", 0xFFA3CED2));
        // Create a task for testing
        taskRepository.insert(new TaskDomain(0, 1, "Test1",new Date().getTime()));


        // Call the getAllProjects method
        LiveData<List<Task>> allTasks = taskViewModel.getAllTasks();

        // Assert taskViewModel projects is equal at repository projects
        assertEquals(allTasks.getValue(),taskRepository.getAllTasks().getValue());
    }

}
