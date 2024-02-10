package com.cleanup.todoc.datasource.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * Entity for the tasks of the application.
 *
 * @author Xavier Carpentier
 */
@Entity(
    tableName = "TaskEntity",
    foreignKeys = [ForeignKey(
        entity = ProjectEntity::class,
        parentColumns = ["id"],
        childColumns = ["projectId"]
    )]
)

data class TaskEntity(
    /**
     * The unique identifier of the task
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    /**
     * The unique identifier of the project associated to the task
     */
    @ColumnInfo(name = "projectId", index = true)
    val projectId: Long,

    /**
     * The name of the task
     */
    // Suppress warning because setName is called in constructor
    @ColumnInfo(name = "name")
    val name: String,

    /**
     * The timestamp when the task has been created
     */
    @ColumnInfo(name = "creationTimestamp")
    val creationTimestamp: Long
)
