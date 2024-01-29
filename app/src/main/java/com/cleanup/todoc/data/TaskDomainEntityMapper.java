package com.cleanup.todoc.data;

import com.cleanup.todoc.datasource.Entity.TaskEntity;
import com.cleanup.todoc.domaine.model.TaskDomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <p>Map for convert TaskEntity in to TaskDomain and TaskDomain in to TaskEntity.</p>
 *
 * @author Xavier Carpentier
 */
public class TaskDomainEntityMapper {
    /**
     * Maps a Room entity Task to a domain object TaskDomain.
     *
     * @param task the Task entity to map
     * @return the mapped TaskDomain object
     */
    public static TaskDomain mapToDomain(TaskEntity task) {
        if (task == null) {
            return null;
        }
        return new TaskDomain(task.getId(),task.getProjectId(), task.getName(), task.getCreationTimestamp());
    }

    /**
     * Maps a domain object TaskDomain to a Room entity Task.
     *
     * @param task the TaskDomain object to map
     * @return the mapped Room entity Task
     */
    public static TaskEntity mapToEntity(TaskDomain task) {
        if (task == null) {
            return null;
        }
        return new TaskEntity(task.getId(),task.getProjectId(), task.getName(), task.getCreationTimestamp());
    }

    /**
     * Maps a list of Room entity Task to a list of domain objects TaskDomains.
     *
     * @param tasks the list of Task entities to map
     * @return the list of mapped TaskDomain objects
     */
    public static List<TaskDomain> mapToDomainList(List<TaskEntity> tasks) {
        if (tasks == null) {
            return Collections.emptyList();
        }
        List<TaskDomain> domainList = new ArrayList<>();
        for (TaskEntity task : tasks) {
            domainList.add(mapToDomain(task));
        }
        return domainList;
    }
}
