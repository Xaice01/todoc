package com.cleanup.todoc.data.repository

import android.app.Application
import com.cleanup.todoc.data.TaskDomainEntityMapper
import com.cleanup.todoc.datasource.dao.TaskDao
import com.cleanup.todoc.datasource.database.TodocRoomDatabase
import com.cleanup.todoc.datasource.entity.TaskEntity
import com.cleanup.todoc.domaine.model.TaskDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(application: Application) {
    private val taskDao: TaskDao
    var allTasks: Flow<List<TaskDomain>>
    var allTasksOrderByProject: Flow<List<TaskDomain>>

    //constructor
    init {
        val db = TodocRoomDatabase.getInstance(application)
        taskDao = db.taskDao()
        allTasks = taskDao.getTasks()
            .map<List<TaskEntity>, List<TaskDomain>> { tasks: List<TaskEntity?>? ->
                TaskDomainEntityMapper.mapToDomainList(tasks)
            }
        allTasksOrderByProject = taskDao.getTasksByProject()
            .map<List<TaskEntity>, List<TaskDomain>> { tasks: List<TaskEntity?>? ->
                TaskDomainEntityMapper.mapToDomainList(tasks)
            }
    }

    suspend fun insert(taskDomain: TaskDomain) {
        taskDao.insert(
            TaskDomainEntityMapper.mapToEntity(
                taskDomain
            )
        )
    }

    suspend fun delete(taskDomain: TaskDomain?) {
        taskDao.delete(
            TaskDomainEntityMapper.mapToEntity(
                taskDomain
            )
        )
    }
}