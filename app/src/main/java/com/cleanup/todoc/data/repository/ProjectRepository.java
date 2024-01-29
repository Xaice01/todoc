package com.cleanup.todoc.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.ProjectDomainEntityMapper;
import com.cleanup.todoc.datasource.Entity.ProjectEntity;
import com.cleanup.todoc.datasource.dao.ProjectDao;
import com.cleanup.todoc.datasource.database.TodocRoomDatabase;
import com.cleanup.todoc.domaine.model.ProjectDomain;

import java.util.List;

public class ProjectRepository {
    private final ProjectDao projectDao;
    LiveData<List<ProjectDomain>> allProjects;


    //constructor
    public ProjectRepository(Application application){
        TodocRoomDatabase db = TodocRoomDatabase.getInstance(application);
        projectDao = db.projectDao();
        LiveData<List<ProjectEntity>> livedata = projectDao.getProjects();
        allProjects = Transformations.map(livedata, ProjectDomainEntityMapper::mapToDomainList);
    }

    public LiveData<List<ProjectDomain>> getAllProjects(){
        return allProjects;
    }

    public void insert(ProjectDomain projectDomain){
        TodocRoomDatabase.executors.execute(() -> {projectDao.insert(ProjectDomainEntityMapper.mapToEntity(projectDomain));});
    }

    public void delete(ProjectDomain projectDomain){
        TodocRoomDatabase.executors.execute(() -> {projectDao.delete(ProjectDomainEntityMapper.mapToEntity(projectDomain));});
    }

    public void deleteAll(){
        TodocRoomDatabase.executors.execute(() -> {projectDao.deleteAll();});
    }

}

