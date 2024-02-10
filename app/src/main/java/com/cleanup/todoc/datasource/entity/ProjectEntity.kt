package com.cleanup.todoc.datasource.entity

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Entity for project in which tasks are included.
 *
 * @author Xavier Carpentier
 */
@Entity(tableName = "ProjectEntity")
data class ProjectEntity(
    /**
     * The unique identifier of the project
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    /**
     * The name of the project
     */
    @ColumnInfo(name = "name")
    val name: String,

    /**
     * The hex (ARGB) code of the color associated to the project
     */
    @ColumnInfo(name = "color")
    @ColorInt
    val color: Int
)
