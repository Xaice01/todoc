package com.cleanup.todoc.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

/*
 * Representation Room of the relation one(Project) to many(Task)
 */
public class ProjectWithTasks {
    @Embedded public Project project;
    @Relation(
            parentColumn = "id",
            entityColumn = "projectId"
    )
    public List<Task> tasks;
}
