package com.cleanup.todoc.datasource.Entity;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * <p>Models for project in which tasks are included.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(tableName = "ProjectEntity")
public class ProjectEntity {
    /**
     * The unique identifier of the project
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final long id;

    /**
     * The name of the project
     */
    @NonNull
    @ColumnInfo(name = "name")
    private final String name;

    /**
     * The hex (ARGB) code of the color associated to the project
     */
    @ColumnInfo(name = "color")
    @ColorInt
    private final int color;

    /**
     * Instantiates a new Project.
     *
     * @param id    the unique identifier of the project to set
     * @param name  the name of the project to set
     * @param color the hex (ARGB) code of the color associated to the project to set
     */
    public ProjectEntity(long id, @NonNull String name, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * Returns the project with the given unique identifier, or null if no project with that
     * identifier can be found.
     *
     * @param id the unique identifier of the project to return
     * @param projectEntities the list of project from Data
     * @return the project with the given unique identifier, or null if it has not been found
     */
    @Nullable
    public static ProjectEntity getProjectById(long id, List<ProjectEntity> projectEntities) {
        for (ProjectEntity projectEntity : projectEntities) {
            if (projectEntity.id == id)
                return projectEntity;
        }
        return null;
    }

    /**
     * Returns the unique identifier of the project.
     *
     * @return the unique identifier of the project
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the hex (ARGB) code of the color associated to the project.
     *
     * @return the hex (ARGB) code of the color associated to the project
     */
    @ColorInt
    public int getColor() {
        return color;
    }

    @Override
    @NonNull
    public String toString() {
        return getName();
    }
}
