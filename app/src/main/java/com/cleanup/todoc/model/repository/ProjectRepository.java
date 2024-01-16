package com.cleanup.todoc.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.dao.ProjectDao;
import com.cleanup.todoc.model.database.TodocRoomDatabase;

import java.util.List;

public class ProjectRepository {
    private ProjectDao projectDao;
    LiveData<List<Project>> allProjects;

    //constructor
    public ProjectRepository(Application application){
        TodocRoomDatabase db = TodocRoomDatabase.getInstance(application);
        projectDao = db.projectDao();
        allProjects = projectDao.getProjects();
    }

    public LiveData<List<Project>> getAllProjects(){
        return allProjects;
    }

    public void insert(Project project){
        TodocRoomDatabase.executors.execute(() -> {projectDao.insert(project);});
    }

    public void delete(Project project){
        TodocRoomDatabase.executors.execute(() -> {projectDao.delete(project);});
    }

    public void deleteAll(){
        TodocRoomDatabase.executors.execute(() -> {projectDao.deleteAll();});
    }

}

