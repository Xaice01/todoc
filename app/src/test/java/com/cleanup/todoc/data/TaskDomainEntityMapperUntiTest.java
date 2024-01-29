package com.cleanup.todoc.data;

import static com.cleanup.todoc.data.TaskDomainEntityMapper.mapToDomain;
import static com.cleanup.todoc.data.TaskDomainEntityMapper.mapToDomainList;
import static com.cleanup.todoc.data.TaskDomainEntityMapper.mapToEntity;
import static org.junit.Assert.assertEquals;

import com.cleanup.todoc.datasource.Entity.ProjectEntity;
import com.cleanup.todoc.datasource.Entity.TaskEntity;
import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.domaine.model.TaskDomain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDomainEntityMapperUntiTest {

    @Test
    public void testmapToDomain(){
        //Given
        ProjectEntity projectEntity = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1);
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp = new Date().getTime();

        TaskEntity taskEntity = new TaskEntity(1,1,"TestTask",creationTimestamp);
        TaskDomain taskDomain = new TaskDomain(1,1,"TestTask",creationTimestamp);

        //When
        TaskDomain taskEntityToTaskDomain = mapToDomain(taskEntity);

        //Then
        assertEquals(taskDomain.getId(),taskEntityToTaskDomain.getId());
        assertEquals(taskDomain.getProjectId(),taskEntityToTaskDomain.getProjectId());
        assertEquals(taskDomain.getName(),taskEntityToTaskDomain.getName());
        assertEquals(taskDomain.getCreationTimestamp(),taskEntityToTaskDomain.getCreationTimestamp());

    }

    @Test
    public void testmapToEntity(){
        //Given
        ProjectEntity projectEntity = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1);
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp = new Date().getTime();

        TaskEntity taskEntity = new TaskEntity(1,1,"TestTask",creationTimestamp);
        TaskDomain taskDomain = new TaskDomain(1,1,"TestTask",creationTimestamp);

        //When
        TaskEntity taskDomainToTaskEntity = mapToEntity(taskDomain);

        //Then
        assertEquals(taskDomain.getId(),taskDomainToTaskEntity.getId());
        assertEquals(taskDomain.getProjectId(),taskDomainToTaskEntity.getProjectId());
        assertEquals(taskDomain.getName(),taskDomainToTaskEntity.getName());
        assertEquals(taskDomain.getCreationTimestamp(),taskDomainToTaskEntity.getCreationTimestamp());
    }

    @Test
    public void testmapToDomainList(){
        //Given
        ProjectEntity projectEntity = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1);
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        long creationTimestamp1 = new Date().getTime();
        long creationTimestamp2 = new Date().getTime();
        long creationTimestamp3 = new Date().getTime();

        List<TaskEntity> taskEntitys = new ArrayList<TaskEntity>();
        taskEntitys.add(new TaskEntity(1,1,"TestTask1",creationTimestamp1));
        taskEntitys.add(new TaskEntity(2,1,"TestTask2",creationTimestamp2));
        taskEntitys.add(new TaskEntity(3,1,"TestTask3",creationTimestamp3));

        List<TaskDomain> taskDomains = new ArrayList<TaskDomain>();
        taskDomains.add(new TaskDomain(1,1,"TestTask1",creationTimestamp1));
        taskDomains.add(new TaskDomain(2,1,"TestTask2",creationTimestamp2));
        taskDomains.add(new TaskDomain(3,1,"TestTask3",creationTimestamp3));


        //When
        List<TaskDomain> taskEntitysToListTaskDomain = mapToDomainList(taskEntitys);

        //Then
        assertEquals(taskDomains.get(0).getId(),taskEntitysToListTaskDomain.get(0).getId());
        assertEquals(taskDomains.get(0).getProjectId(),taskEntitysToListTaskDomain.get(0).getProjectId());
        assertEquals(taskDomains.get(0).getName(),taskEntitysToListTaskDomain.get(0).getName());
        assertEquals(taskDomains.get(0).getCreationTimestamp(),taskEntitysToListTaskDomain.get(0).getCreationTimestamp());

        assertEquals(taskDomains.get(1).getId(),taskEntitysToListTaskDomain.get(1).getId());
        assertEquals(taskDomains.get(1).getProjectId(),taskEntitysToListTaskDomain.get(1).getProjectId());
        assertEquals(taskDomains.get(1).getName(),taskEntitysToListTaskDomain.get(1).getName());
        assertEquals(taskDomains.get(1).getCreationTimestamp(),taskEntitysToListTaskDomain.get(1).getCreationTimestamp());

        assertEquals(taskDomains.get(2).getId(),taskEntitysToListTaskDomain.get(2).getId());
        assertEquals(taskDomains.get(2).getProjectId(),taskEntitysToListTaskDomain.get(2).getProjectId());
        assertEquals(taskDomains.get(2).getName(),taskEntitysToListTaskDomain.get(2).getName());
        assertEquals(taskDomains.get(2).getCreationTimestamp(),taskEntitysToListTaskDomain.get(2).getCreationTimestamp());
    }
}