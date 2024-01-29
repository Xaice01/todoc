package com.cleanup.todoc.domain;

import static com.cleanup.todoc.domaine.TaskDomainUiMapper.mapToTask;
import static com.cleanup.todoc.domaine.TaskDomainUiMapper.mapToTaskList;
import static org.junit.Assert.assertEquals;

import com.cleanup.todoc.domaine.TaskDomainUiMapper;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Project;
import com.cleanup.todoc.presentation.model.Task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDomainUiMapperUnitTest {

    @Test
    public void testMapToTask(){
        //Given
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp = new Date().getTime();

        TaskDomain taskDomain = new TaskDomain(1,1,"TestTask",creationTimestamp);
        Task task = new Task(1,1,"TestTask",creationTimestamp);

        //When
        Task taskDomainToTask = mapToTask(taskDomain);

        //Then
        assertEquals(task.getId(),taskDomainToTask.getId());
        assertEquals(task.getProjectId(),taskDomainToTask.getProjectId());
        assertEquals(task.getName(),taskDomainToTask.getName());
        assertEquals(task.getCreationTimestamp(),taskDomainToTask.getCreationTimestamp());

    }

    @Test
    public void testMapToDomain(){
        //Given
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp = new Date().getTime();

        TaskDomain taskDomain = new TaskDomain(1,1,"TestTask",creationTimestamp);
        Task task = new Task(1,1,"TestTask",creationTimestamp);

        //When
        TaskDomain taskToTaskDomain = TaskDomainUiMapper.mapToDomain(task);

        //Then
        assertEquals(task.getId(),taskToTaskDomain.getId());
        assertEquals(task.getProjectId(),taskToTaskDomain.getProjectId());
        assertEquals(task.getName(),taskToTaskDomain.getName());
        assertEquals(task.getCreationTimestamp(),taskToTaskDomain.getCreationTimestamp());
    }

    @Test
    public void testMapToTaskList(){
        //Given
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp1 = new Date().getTime();
        long creationTimestamp2 = new Date().getTime();
        long creationTimestamp3 = new Date().getTime();

        List<TaskDomain> taskDomains = new ArrayList<TaskDomain>();
        taskDomains.add(new TaskDomain(1,1,"TestTask1",creationTimestamp1));
        taskDomains.add(new TaskDomain(2,1,"TestTask2",creationTimestamp2));
        taskDomains.add(new TaskDomain(3,1,"TestTask3",creationTimestamp3));

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1,1,"TestTask1",creationTimestamp1));
        tasks.add(new Task(2,1,"TestTask2",creationTimestamp2));
        tasks.add(new Task(3,1,"TestTask3",creationTimestamp3));


        //When
        List<Task> taskDomainsToListTask = mapToTaskList(taskDomains);

        //Then
        assertEquals(tasks.get(0).getId(),taskDomainsToListTask.get(0).getId());
        assertEquals(tasks.get(0).getProjectId(),taskDomainsToListTask.get(0).getProjectId());
        assertEquals(tasks.get(0).getName(),taskDomainsToListTask.get(0).getName());
        assertEquals(tasks.get(0).getCreationTimestamp(),taskDomainsToListTask.get(0).getCreationTimestamp());

        assertEquals(tasks.get(1).getId(),taskDomainsToListTask.get(1).getId());
        assertEquals(tasks.get(1).getProjectId(),taskDomainsToListTask.get(1).getProjectId());
        assertEquals(tasks.get(1).getName(),taskDomainsToListTask.get(1).getName());
        assertEquals(tasks.get(1).getCreationTimestamp(),taskDomainsToListTask.get(1).getCreationTimestamp());

        assertEquals(tasks.get(2).getId(),taskDomainsToListTask.get(2).getId());
        assertEquals(tasks.get(2).getProjectId(),taskDomainsToListTask.get(2).getProjectId());
        assertEquals(tasks.get(2).getName(),taskDomainsToListTask.get(2).getName());
        assertEquals(tasks.get(2).getCreationTimestamp(),taskDomainsToListTask.get(2).getCreationTimestamp());
    }

}