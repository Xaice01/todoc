package com.cleanup.todoc.datasource.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cleanup.todoc.datasource.Entity.ProjectEntity;
import com.cleanup.todoc.datasource.Entity.ProjectWithTasks;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProjectEntity projectEntity);

    @Delete
    void delete(ProjectEntity projectEntity);

    @Query("DELETE FROM ProjectEntity")
    void deleteAll();

    @Query("SELECT * FROM ProjectEntity ORDER BY id")
    LiveData<List<ProjectEntity>> getProjects();

    @Transaction
    @Query("SELECT * FROM ProjectEntity")
    public List<ProjectWithTasks> getProjectWithTasks();
}
