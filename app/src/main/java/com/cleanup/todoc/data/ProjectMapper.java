package com.cleanup.todoc.data;

import com.cleanup.todoc.datasource.model.ProjectEntity;
import com.cleanup.todoc.domaine.model.ProjectDomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectMapper {

    /**
     * Maps a Room entity Project to a domain object ProjectDomain.
     *
     * @param project the Project entity to map
     * @return the mapped ProjectDomain object
     */
    public static ProjectDomain mapToDomain(ProjectEntity project) {
        if (project == null) {
            return null;
        }
        return new ProjectDomain(project.getId(), project.getName(), project.getColor());
    }

    /**
     * Maps a domain object ProjectDomain to a Room entity Project.
     *
     * @param project the ProjectDomain object to map
     * @return the mapped Room entity Project
     */
    public static ProjectEntity mapToEntity(ProjectDomain project) {
        if (project == null) {
            return null;
        }
        return new ProjectEntity(project.getId(), project.getName(), project.getColor());
    }


    /**
     * Maps a list of Room entity Projects to a list of domain objects ProjectDomains.
     *
     * @param projects the list of Project entities to map
     * @return the list of mapped ProjectDomain objects
     */
    public static List<ProjectDomain> mapToDomainList(List<ProjectEntity> projects) {
        if (projects == null) {
            return Collections.emptyList();
        }
        List<ProjectDomain> domainList = new ArrayList<>();
        for (ProjectEntity project : projects) {
            domainList.add(mapToDomain(project));
        }
        return domainList;
    }
}