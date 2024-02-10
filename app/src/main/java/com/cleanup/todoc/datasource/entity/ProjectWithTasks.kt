package com.cleanup.todoc.datasource.entity

import androidx.room.Embedded
import androidx.room.Relation

/*
 * Representation Room of the relation one(Project) to many(Task)
 */
class ProjectWithTasks {
    @JvmField
    @Embedded
    var projectEntity: ProjectEntity? = null

    @JvmField
    @Relation(parentColumn = "id", entityColumn = "projectId")
    var taskEntities: List<TaskEntity>? = null
}
