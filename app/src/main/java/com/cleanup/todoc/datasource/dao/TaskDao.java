package com.cleanup.todoc.datasource.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.datasource.model.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskEntity taskEntity);

    @Delete
    void delete(TaskEntity taskEntity);

    @Query("DELETE FROM TaskEntity")
    void deleteAll();

    @Query("SELECT * FROM TaskEntity")
    LiveData<List<TaskEntity>> getTasks();

    @Query("SELECT * FROM TaskEntity ORDER BY projectId")
    LiveData<List<TaskEntity>> getTasksByProject();
}
