package com.cleanup.todoc.datasource.Entity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Entity for the tasks of the application.</p>
 *
 * @author Xavier Carpentier
 */
@Entity( tableName = "TaskEntity" ,foreignKeys = {@ForeignKey(entity = ProjectEntity.class,
        parentColumns = "id",
        childColumns = "projectId")

})
public class TaskEntity {
    /**
     * The unique identifier of the task
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    /**
     * The unique identifier of the project associated to the task
     */
    @ColumnInfo(name = "projectId", index = true)
    private long projectId;

    /**
     * The name of the task
     */
    // Suppress warning because setName is called in constructor
    @SuppressWarnings("NullableProblems")
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    /**
     * The timestamp when the task has been created
     */
    @ColumnInfo(name = "creationTimestamp")
    private long creationTimestamp;

    /**
     * Instantiates a new Task.
     *
     * @param id                the unique identifier of the task to set
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    public TaskEntity(long id, long projectId, @NonNull String name, long creationTimestamp) {
        this.setId(id);
        this.setProjectId(projectId);
        this.setName(name);
        this.setCreationTimestamp(creationTimestamp);
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return the unique identifier of the task
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique idenifier of the task to set
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Return the projectId
     *
     * @return  the projectId of the task
     */
    public long getProjectId(){
        return projectId;
    }
    /**
     * Sets the unique identifier of the project associated to the task.
     *
     * @param projectId the unique identifier of the project associated to the task to set
     */
    private void setProjectId(long projectId) {
        this.projectId = projectId;
    }


    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the name of the task to set
     */
    private void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Return the timestamp
     *
     * @return  the timestamp of the task
     */
    public long getCreationTimestamp(){
        return creationTimestamp;
    }
    /**
     * Sets the timestamp when the task has been created.
     *
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
