package com.cleanup.todoc.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.dao.TaskDao;
import com.cleanup.todoc.model.database.TodocRoomDatabase;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    LiveData<List<Task>> allTasks;
    LiveData<List<Task>> allTasksOrderByProject;

    //constructor
    public TaskRepository(Application application){
        TodocRoomDatabase db = TodocRoomDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getTasks();
        allTasksOrderByProject = taskDao.getTasksByProject();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public LiveData<List<Task>> getAllTasksOrderByProject(){
        return allTasksOrderByProject;
    }

    public void insert(Task task){
        TodocRoomDatabase.executors.execute(() -> {taskDao.insert(task);});
    }

    public void delete(Task task){
        TodocRoomDatabase.executors.execute(() -> {taskDao.delete(task);});
    }

    public void deleteAll(){
        TodocRoomDatabase.executors.execute(() -> {taskDao.deleteAll();});
    }

}