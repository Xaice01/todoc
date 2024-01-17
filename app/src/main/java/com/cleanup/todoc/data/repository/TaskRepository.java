package com.cleanup.todoc.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.ProjectMapper;
import com.cleanup.todoc.data.TaskMapper;
import com.cleanup.todoc.datasource.model.TaskEntity;
import com.cleanup.todoc.datasource.dao.TaskDao;
import com.cleanup.todoc.datasource.database.TodocRoomDatabase;
import com.cleanup.todoc.domaine.model.TaskDomain;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    LiveData<List<TaskDomain>> allTasks;
    LiveData<List<TaskDomain>> allTasksOrderByProject;

    //constructor
    public TaskRepository(Application application){
        TodocRoomDatabase db = TodocRoomDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = Transformations.map(taskDao.getTasks(), TaskMapper::mapToDomainList);
        allTasksOrderByProject = Transformations.map(taskDao.getTasksByProject(), TaskMapper::mapToDomainList);
    }

    public LiveData<List<TaskDomain>> getAllTasks(){
        return allTasks;
    }

    public LiveData<List<TaskDomain>> getAllTasksOrderByProject(){
        return allTasksOrderByProject;
    }

    public void insert(TaskDomain taskDomain){
        TodocRoomDatabase.executors.execute(() -> {taskDao.insert(TaskMapper.mapToEntity(taskDomain));});
    }

    public void delete(TaskDomain taskDomain){
        TodocRoomDatabase.executors.execute(() -> {taskDao.delete(TaskMapper.mapToEntity(taskDomain));});
    }

    public void deleteAll(){
        TodocRoomDatabase.executors.execute(() -> {taskDao.deleteAll();});
    }

}