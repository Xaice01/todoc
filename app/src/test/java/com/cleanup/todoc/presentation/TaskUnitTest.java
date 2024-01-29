package com.cleanup.todoc.presentation;

import com.cleanup.todoc.presentation.model.Project;
import com.cleanup.todoc.presentation.model.Task;

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
public class TaskUnitTest {
    @Test
    public void test_projects() {
        List<Project> project =new ArrayList<Project>();
            project.add(new Project(1L, "Projet Tartampion", 0xFFEADAD1));
            project.add(new Project(2L, "Projet Lucidia", 0xFFB4CDBA));
            project.add(new Project(3L, "Projet Circus", 0xFFA3CED2));

        final Task task1 = new Task(1, 1, "task 1", new Date().getTime());
        final Task task2 = new Task(2, 2, "task 2", new Date().getTime());
        final Task task3 = new Task(3, 3, "task 3", new Date().getTime());
        final Task task4 = new Task(4, 4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", task1.getProject(project).getName());
        assertEquals("Projet Lucidia", task2.getProject(project).getName());
        assertEquals("Projet Circus", task3.getProject(project).getName());
        assertNull(task4.getProject(project));
    }

    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> task = new ArrayList<>();
        task.add(task1);
        task.add(task2);
        task.add(task3);
        Collections.sort(task, new Task.TaskAZComparator());

        assertSame(task.get(0), task1);
        assertSame(task.get(1), task3);
        assertSame(task.get(2), task2);
    }

    @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> task = new ArrayList<>();
        task.add(task1);
        task.add(task2);
        task.add(task3);
        Collections.sort(task, new Task.TaskZAComparator());

        assertSame(task.get(0), task2);
        assertSame(task.get(1), task3);
        assertSame(task.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> task = new ArrayList<>();
        task.add(task1);
        task.add(task2);
        task.add(task3);
        Collections.sort(task, new Task.TaskRecentComparator());

        assertSame(task.get(0), task3);
        assertSame(task.get(1), task2);
        assertSame(task.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> task = new ArrayList<>();
        task.add(task1);
        task.add(task2);
        task.add(task3);
        Collections.sort(task, new Task.TaskOldComparator());

        assertSame(task.get(0), task1);
        assertSame(task.get(1), task2);
        assertSame(task.get(2), task3);
    }
}