package com.cleanup.todoc.dao;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.datasource.Entity.ProjectEntity;
import com.cleanup.todoc.datasource.Entity.TaskEntity;
import com.cleanup.todoc.datasource.dao.ProjectDao;
import com.cleanup.todoc.datasource.dao.TaskDao;
import com.cleanup.todoc.datasource.database.TodocRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private static final ProjectEntity project1 = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1) ;
    private static final ProjectEntity project2 = new ProjectEntity(2L, "Projet Lucidia", 0xFFB4CDBA);
    private static final ProjectEntity project3 = new ProjectEntity(3L, "Projet Circus", 0xFFA3CED2);

    static long creationTimestamp1 = new Date().getTime();
    private static final TaskEntity task1 = new TaskEntity(1,2L,"Test Task 1", creationTimestamp1);
    static long creationTimestamp2 = new Date().getTime();
    private static final TaskEntity task2 = new TaskEntity(2,1L,"Test Task 2", creationTimestamp2);
    static long creationTimestamp3 = new Date().getTime();
    private static final TaskEntity task3 = new TaskEntity(3,3L,"Test Task 3", creationTimestamp3);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TodocRoomDatabase database;
    private ProjectDao projectDao;
    private TaskDao taskDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room
                .inMemoryDatabaseBuilder(context, TodocRoomDatabase.class)
                .build();
        projectDao = database.projectDao();
        taskDao = database.taskDao();

        projectDao.insert(project1);
        projectDao.insert(project2);
        projectDao.insert(project3);

    }
    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void testInsert(){
        //When
        taskDao.insert(task1);
        List<TaskEntity> tasks = TestUtilsDao.getValue(taskDao.getTasks());

        //Then
        assertEquals(1,tasks.size());
    }

    @Test
    public void testDelete(){
        //Given
        taskDao.insert(task2);
        List<TaskEntity> tasksGiven = TestUtilsDao.getValue(taskDao.getTasks());
        assertEquals(1,tasksGiven.size());

        //When
        taskDao.delete(task2);

        //Then
        List<TaskEntity> tasksThen = TestUtilsDao.getValue(taskDao.getTasks());
        assertEquals(0,tasksThen.size());
    }

    @Test
    public void testGetTask(){
        //Given
        taskDao.insert(task1);
        taskDao.insert(task2);
        taskDao.insert(task3);

        //When
        List<TaskEntity> tasks = TestUtilsDao.getValue(taskDao.getTasks());

        //Then
        assertEquals(3,tasks.size());
        assertEquals(task1.getId(),tasks.get(0).getId());
        assertEquals(task2.getId(),tasks.get(1).getId());
        assertEquals(task3.getId(),tasks.get(2).getId());
    }

    @Test
    public void testGetTasksByProject(){
        //Given
        taskDao.insert(task1);
        taskDao.insert(task2);
        taskDao.insert(task3);

        //When
        List<TaskEntity> tasks = TestUtilsDao.getValue(taskDao.getTasksByProject());

        //Then
        assertEquals(3,tasks.size());
        assertEquals(task2.getId(),tasks.get(0).getId());
        assertEquals(task1.getId(),tasks.get(1).getId());
        assertEquals(task3.getId(),tasks.get(2).getId());
    }

}
