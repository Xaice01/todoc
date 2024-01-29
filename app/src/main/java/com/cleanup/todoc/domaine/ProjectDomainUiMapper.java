package com.cleanup.todoc.domaine;

import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.presentation.model.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Map for convert ProjectDomain in to Project and project in to ProjectDomain.</p>
 *
 * @author Xavier Carpentier
 */
public class ProjectDomainUiMapper {

    /**
     * Maps ProjectDomain to a ui object Project.
     *
     * @param project the ProjectDomain to map
     * @return the mapped Project object
     */
    public static Project mapToProject(ProjectDomain project) {
        if (project == null) {
            return null;
        }
        return new Project(project.getId(), project.getName(), project.getColor());
    }

    /**
     * Maps a ui object Project to a domain object ProjectDomain.
     *
     * @param project the Project object to map
     * @return the mapped ProjectDomain
     */
    public static ProjectDomain mapToDomain(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectDomain(project.getId(), project.getName(), project.getColor());
    }


    /**
     * Maps a list of ProjectsDomain to a list of domain objects Project.
     *
     * @param projects the list of ProjectDomain to map
     * @return the list of mapped Project objects
     */
    public static List<Project> mapToProjectList(List<ProjectDomain> projects) {
        if (projects == null) {
            return Collections.emptyList();
        }
        List<Project> domainList = new ArrayList<>();
        for (ProjectDomain project : projects) {
            domainList.add(mapToProject(project));
        }
        return domainList;
    }
}