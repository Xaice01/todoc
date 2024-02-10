package com.cleanup.todoc.dao;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.datasource.entity.ProjectEntity;
import com.cleanup.todoc.datasource.dao.ProjectDao;
import com.cleanup.todoc.datasource.database.TodocRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    private static final ProjectEntity project1 = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1) ;
    private static final ProjectEntity project2 = new ProjectEntity(2L, "Projet Lucidia", 0xFFB4CDBA);
    private static final ProjectEntity project3 = new ProjectEntity(3L, "Projet Circus", 0xFFA3CED2);


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TodocRoomDatabase database;
    private ProjectDao projectDao;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room
                .inMemoryDatabaseBuilder(context, TodocRoomDatabase.class)
                .build();
        projectDao = database.projectDao();
    }
    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void testInsert(){
        //When
        projectDao.insert(project1);
        List<ProjectEntity> projects = TestUtilsDao.getValue(projectDao.getProjects());

        //Then
        assertEquals(1,projects.size());
    }

    @Test
    public void testDelete(){
        //Given
        projectDao.insert(project2);
        List<ProjectEntity> projectsGiven = TestUtilsDao.getValue(projectDao.getProjects());
        assertEquals(1,projectsGiven.size());

        //When
        projectDao.delete(project2);

        //Then
        List<ProjectEntity> projectsThen = TestUtilsDao.getValue(projectDao.getProjects());
        assertEquals(0,projectsThen.size());
    }

    @Test
    public void testGetProjects(){
        //Given
        projectDao.insert(project1);
        projectDao.insert(project2);
        projectDao.insert(project3);

        //When
        List<ProjectEntity> projects = TestUtilsDao.getValue(projectDao.getProjects());

        //Then
        assertEquals(3,projects.size());
        assertEquals(project1.getId(),projects.get(0).getId());
        assertEquals(project2.getId(),projects.get(1).getId());
        assertEquals(project3.getId(),projects.get(2).getId());
    }

    @Test
    public void testDeleteAll(){
        //Given
        projectDao.insert(project1);
        projectDao.insert(project2);
        projectDao.insert(project3);
        List<ProjectEntity> projectsGiven = TestUtilsDao.getValue(projectDao.getProjects());
        assertEquals(3,projectsGiven.size());

        //When
        projectDao.deleteAll();

        //Then
        List<ProjectEntity> projectsThen = TestUtilsDao.getValue(projectDao.getProjects());
        assertEquals(0,projectsThen.size());
    }

}
