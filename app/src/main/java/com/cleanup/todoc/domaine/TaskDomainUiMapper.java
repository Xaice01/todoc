package com.cleanup.todoc.domaine;

import com.cleanup.todoc.domaine.model.TaskDomain;
import com.cleanup.todoc.presentation.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <p>Map for convert TaskDomain in to Task and Task in to TaskDomain.</p>
 *
 * @author Xavier Carpentier
 */
public class TaskDomainUiMapper {
    /**
     * Maps a TaskDomain to a domain object Task.
     *
     * @param task the TaskDomain to map
     * @return the mapped Task object
     */
    public static Task mapToTask(TaskDomain task) {
        if (task == null) {
            return null;
        }
        return new Task(task.getId(),task.getProjectId(), task.getName(), task.getCreationTimestamp());
    }

    /**
     * Maps a domain object Task to a TaskDomain.
     *
     * @param task the Task object to map
     * @return the mapped TaskDomain
     */
    public static TaskDomain mapToDomain(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDomain(task.getId(),task.getProjectId(), task.getName(), task.getCreationTimestamp());
    }

    /**
     * Maps a list of TaskDomains to a list of ui objects Tasks.
     *
     * @param tasks the list of TaskDomains to map
     * @return the list of mapped Tasks objects
     */
    public static List<Task> mapToTaskList(List<TaskDomain> tasks) {
        if (tasks == null) {
            return Collections.emptyList();
        }
        List<Task> list = new ArrayList<>();
        for (TaskDomain task : tasks) {
            list.add(mapToTask(task));
        }
        return list;
    }
}
