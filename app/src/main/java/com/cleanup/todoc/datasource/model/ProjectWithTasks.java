package com.cleanup.todoc.datasource.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/*
 * Representation Room of the relation one(Project) to many(Task)
 */
public class ProjectWithTasks {
    @Embedded
    public ProjectEntity projectEntity;
    @Relation(
            parentColumn = "id",
            entityColumn = "projectId"
    )
    public List<TaskEntity> taskEntities;
}
