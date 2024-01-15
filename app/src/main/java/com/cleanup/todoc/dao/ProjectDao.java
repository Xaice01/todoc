package com.cleanup.todoc.dao;

import androidx.room.Query;
import androidx.room.Transaction;

import com.cleanup.todoc.model.ProjectWithTasks;

import java.util.List;

public interface ProjectDao {
    @Transaction
    @Query("SELECT * FROM Project")
    public List<ProjectWithTasks> getProjectWithTasks();
}
