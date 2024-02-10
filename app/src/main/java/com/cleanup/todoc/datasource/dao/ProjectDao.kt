package com.cleanup.todoc.datasource.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cleanup.todoc.datasource.entity.ProjectEntity
import com.cleanup.todoc.datasource.entity.ProjectWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(projectEntity: ProjectEntity)

    @Delete
    suspend fun delete(projectEntity: ProjectEntity)

    @Query("DELETE FROM ProjectEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM ProjectEntity ORDER BY id")
    fun getProjects(): Flow<List<ProjectEntity>>

    @Transaction
    @Query("SELECT * FROM ProjectEntity")
    suspend fun getProjectWithTasks(): List<ProjectWithTasks>
}
