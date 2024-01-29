package com.cleanup.todoc.domain;

import static com.cleanup.todoc.domaine.ProjectDomainUiMapper.mapToDomain;
import static com.cleanup.todoc.domaine.ProjectDomainUiMapper.mapToProject;
import static com.cleanup.todoc.domaine.ProjectDomainUiMapper.mapToProjectList;

import static org.junit.Assert.assertEquals;

import com.cleanup.todoc.domaine.model.ProjectDomain;
import com.cleanup.todoc.presentation.model.Project;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectDomainUiMapperUnitTest {

    @Test
    public void testMapToProject(){
        //Given
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);

        //When
        Project projectDomainToProject = mapToProject(projectDomain);

        //Then
        assertEquals(project.getId(),projectDomainToProject.getId());
        assertEquals(project.getName(),projectDomainToProject.getName());
        assertEquals(project.getColor(),projectDomainToProject.getColor());
    }

    @Test
    public void testMapToDomain(){
        //Given
        ProjectDomain projectDomain = new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);
        Project project = new Project(1L, "Projet Tartampion", 0xFFEADAD1);

        //When
        ProjectDomain projectToProjectDomain = mapToDomain(project);

        //Then
        assertEquals(project.getId(),projectToProjectDomain.getId());
        assertEquals(project.getName(),projectToProjectDomain.getName());
        assertEquals(project.getColor(),projectToProjectDomain.getColor());
    }

    @Test
    public void testMapToProjectList(){
        //Given
        List<Project> project =new ArrayList<Project>();
        project.add(new Project(1L, "Projet Tartampion", 0xFFEADAD1));
        project.add(new Project(2L, "Projet Lucidia", 0xFFB4CDBA));
        project.add(new Project(3L, "Projet Circus", 0xFFA3CED2));

        List<ProjectDomain> projectDomains =new ArrayList<ProjectDomain>();
        projectDomains.add(new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1));
        projectDomains.add(new ProjectDomain(2L, "Projet Lucidia", 0xFFB4CDBA));
        projectDomains.add(new ProjectDomain(3L, "Projet Circus", 0xFFA3CED2));

        //When
        List<Project> projectDomainsToListProject = mapToProjectList(projectDomains);

        //Then
        assertEquals(project.get(0).getId(),projectDomainsToListProject.get(0).getId());
        assertEquals(project.get(0).getName(),projectDomainsToListProject.get(0).getName());
        assertEquals(project.get(0).getColor(),projectDomainsToListProject.get(0).getColor());

        assertEquals(project.get(1).getId(),projectDomainsToListProject.get(1).getId());
        assertEquals(project.get(1).getName(),projectDomainsToListProject.get(1).getName());
        assertEquals(project.get(1).getColor(),projectDomainsToListProject.get(1).getColor());

        assertEquals(project.get(2).getId(),projectDomainsToListProject.get(2).getId());
        assertEquals(project.get(2).getName(),projectDomainsToListProject.get(2).getName());
        assertEquals(project.get(2).getColor(),projectDomainsToListProject.get(2).getColor());
    }
}