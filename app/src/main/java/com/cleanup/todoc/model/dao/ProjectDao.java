package com.cleanup.todoc.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.ProjectWithTasks;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Project project);

    @Query("DELETE FROM Project")
    void deleteAll();

    @Query("SELECT * FROM Project ORDER BY id")
    LiveData<Project> getProjects();

    @Transaction
    @Query("SELECT * FROM Project")
    public List<ProjectWithTasks> getProjectWithTasks();
}
