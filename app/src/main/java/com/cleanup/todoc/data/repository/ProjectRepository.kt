package com.cleanup.todoc.data.repository

import android.app.Application
import com.cleanup.todoc.data.ProjectDomainEntityMapper
import com.cleanup.todoc.datasource.dao.ProjectDao
import com.cleanup.todoc.datasource.database.TodocRoomDatabase
import com.cleanup.todoc.datasource.entity.ProjectEntity
import com.cleanup.todoc.domaine.model.ProjectDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(application: Application?) {
    private val projectDao: ProjectDao
    @JvmField
    var allProjects: Flow<List<ProjectDomain>>

    //constructor
    init {
        val db = TodocRoomDatabase.getInstance(application)
        projectDao = db.projectDao()
        val projectFlow = projectDao.getProjects()
        allProjects = projectFlow.map { projects: List<ProjectEntity> ->
            ProjectDomainEntityMapper.mapToDomainList(projects)
        }
    }

    suspend fun insert(projectDomain: ProjectDomain) {
            projectDao.insert(
                ProjectDomainEntityMapper.mapToEntity(
                    projectDomain
                )
            )
    }

    suspend fun delete(projectDomain: ProjectDomain) {
            projectDao.delete(
                ProjectDomainEntityMapper.mapToEntity(
                    projectDomain
                )
            )
    }

    suspend fun deleteAll() {
        projectDao.deleteAll()
    }
}
