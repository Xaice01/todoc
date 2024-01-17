package com.cleanup.todoc;

import com.cleanup.todoc.datasource.model.ProjectEntity;
import com.cleanup.todoc.datasource.model.TaskEntity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
public class TaskEntityUnitTest {
    @Test
    public void test_projects() {
        List<ProjectEntity> projectEntities =new ArrayList<ProjectEntity>();
            projectEntities.add(new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1));
            projectEntities.add(new ProjectEntity(2L, "Projet Lucidia", 0xFFB4CDBA));
            projectEntities.add(new ProjectEntity(3L, "Projet Circus", 0xFFA3CED2));

        final TaskEntity taskEntity1 = new TaskEntity(1, 1, "task 1", new Date().getTime());
        final TaskEntity taskEntity2 = new TaskEntity(2, 2, "task 2", new Date().getTime());
        final TaskEntity taskEntity3 = new TaskEntity(3, 3, "task 3", new Date().getTime());
        final TaskEntity taskEntity4 = new TaskEntity(4, 4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", taskEntity1.getProject(projectEntities).getName());
        assertEquals("Projet Lucidia", taskEntity2.getProject(projectEntities).getName());
        assertEquals("Projet Circus", taskEntity3.getProject(projectEntities).getName());
        assertNull(taskEntity4.getProject(projectEntities));
    }

    @Test
    public void test_az_comparator() {
        final TaskEntity taskEntity1 = new TaskEntity(1, 1, "aaa", 123);
        final TaskEntity taskEntity2 = new TaskEntity(2, 2, "zzz", 124);
        final TaskEntity taskEntity3 = new TaskEntity(3, 3, "hhh", 125);

        final ArrayList<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(taskEntity1);
        taskEntities.add(taskEntity2);
        taskEntities.add(taskEntity3);
        Collections.sort(taskEntities, new TaskEntity.TaskAZComparator());

        assertSame(taskEntities.get(0), taskEntity1);
        assertSame(taskEntities.get(1), taskEntity3);
        assertSame(taskEntities.get(2), taskEntity2);
    }

    @Test
    public void test_za_comparator() {
        final TaskEntity taskEntity1 = new TaskEntity(1, 1, "aaa", 123);
        final TaskEntity taskEntity2 = new TaskEntity(2, 2, "zzz", 124);
        final TaskEntity taskEntity3 = new TaskEntity(3, 3, "hhh", 125);

        final ArrayList<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(taskEntity1);
        taskEntities.add(taskEntity2);
        taskEntities.add(taskEntity3);
        Collections.sort(taskEntities, new TaskEntity.TaskZAComparator());

        assertSame(taskEntities.get(0), taskEntity2);
        assertSame(taskEntities.get(1), taskEntity3);
        assertSame(taskEntities.get(2), taskEntity1);
    }

    @Test
    public void test_recent_comparator() {
        final TaskEntity taskEntity1 = new TaskEntity(1, 1, "aaa", 123);
        final TaskEntity taskEntity2 = new TaskEntity(2, 2, "zzz", 124);
        final TaskEntity taskEntity3 = new TaskEntity(3, 3, "hhh", 125);

        final ArrayList<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(taskEntity1);
        taskEntities.add(taskEntity2);
        taskEntities.add(taskEntity3);
        Collections.sort(taskEntities, new TaskEntity.TaskRecentComparator());

        assertSame(taskEntities.get(0), taskEntity3);
        assertSame(taskEntities.get(1), taskEntity2);
        assertSame(taskEntities.get(2), taskEntity1);
    }

    @Test
    public void test_old_comparator() {
        final TaskEntity taskEntity1 = new TaskEntity(1, 1, "aaa", 123);
        final TaskEntity taskEntity2 = new TaskEntity(2, 2, "zzz", 124);
        final TaskEntity taskEntity3 = new TaskEntity(3, 3, "hhh", 125);

        final ArrayList<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(taskEntity1);
        taskEntities.add(taskEntity2);
        taskEntities.add(taskEntity3);
        Collections.sort(taskEntities, new TaskEntity.TaskOldComparator());

        assertSame(taskEntities.get(0), taskEntity1);
        assertSame(taskEntities.get(1), taskEntity2);
        assertSame(taskEntities.get(2), taskEntity3);
    }
}