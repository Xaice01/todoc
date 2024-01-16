package com.cleanup.todoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM Task")
    void deleteAll();

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM Task ORDER BY projectId")
    LiveData<List<Task>> getTasksByProject();
}
