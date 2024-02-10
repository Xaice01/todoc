package com.cleanup.todoc.data;


import static com.cleanup.todoc.data.ProjectDomainEntityMapper.mapToDomain;
import static com.cleanup.todoc.data.ProjectDomainEntityMapper.mapToDomainList;
import static com.cleanup.todoc.data.ProjectDomainEntityMapper.mapToEntity;
import static org.junit.Assert.assertEquals;

import com.cleanup.todoc.datasource.entity.ProjectEntity;
import com.cleanup.todoc.domaine.model.ProjectDomain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectDomainEntityMapperUntiTest {
    @Test
    public void testMapToDomain(){
        //Given
        ProjectEntity projectEntity = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1);
        com.cleanup.todoc.domaine.model.ProjectDomain projectDomain = new com.cleanup.todoc.domaine.model.ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);

        //When
        com.cleanup.todoc.domaine.model.ProjectDomain projectEntityToProjectDomain = mapToDomain(projectEntity);

        //Then
        assertEquals(projectDomain.getId(),projectEntityToProjectDomain.getId());
        assertEquals(projectDomain.getName(),projectEntityToProjectDomain.getName());
        assertEquals(projectDomain.getColor(),projectEntityToProjectDomain.getColor());
    }

    @Test
    public void testMapToEntiy(){
        //Given
        ProjectEntity projectEntity = new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1);
        com.cleanup.todoc.domaine.model.ProjectDomain projectDomain = new com.cleanup.todoc.domaine.model.ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1);

        //When
        ProjectEntity projectDomainToProjectEntity = mapToEntity(projectDomain);

        //Then
        assertEquals(projectEntity.getId(),projectDomainToProjectEntity.getId());
        assertEquals(projectEntity.getName(),projectDomainToProjectEntity.getName());
        assertEquals(projectEntity.getColor(),projectDomainToProjectEntity.getColor());
    }

    @Test
    public void testmapToDomainList() {
        //Given
        List<ProjectDomain> projectDomains = new ArrayList<ProjectDomain>();
        projectDomains.add(new ProjectDomain(1L, "Projet Tartampion", 0xFFEADAD1));
        projectDomains.add(new ProjectDomain(2L, "Projet Lucidia", 0xFFB4CDBA));
        projectDomains.add(new ProjectDomain(3L, "Projet Circus", 0xFFA3CED2));

        List<ProjectEntity> projectEntitys = new ArrayList<ProjectEntity>();
        projectEntitys.add(new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1));
        projectEntitys.add(new ProjectEntity(2L, "Projet Lucidia", 0xFFB4CDBA));
        projectEntitys.add(new ProjectEntity(3L, "Projet Circus", 0xFFA3CED2));

        //When
        List<ProjectDomain> projectEntityToListProjectDomain = mapToDomainList(projectEntitys);

        //Then
        assertEquals(projectDomains.get(0).getId(), projectEntityToListProjectDomain.get(0).getId());
        assertEquals(projectDomains.get(0).getName(), projectEntityToListProjectDomain.get(0).getName());
        assertEquals(projectDomains.get(0).getColor(), projectEntityToListProjectDomain.get(0).getColor());

        assertEquals(projectDomains.get(1).getId(), projectEntityToListProjectDomain.get(1).getId());
        assertEquals(projectDomains.get(1).getName(), projectEntityToListProjectDomain.get(1).getName());
        assertEquals(projectDomains.get(1).getColor(), projectEntityToListProjectDomain.get(1).getColor());

        assertEquals(projectDomains.get(2).getId(), projectEntityToListProjectDomain.get(2).getId());
        assertEquals(projectDomains.get(2).getName(), projectEntityToListProjectDomain.get(2).getName());
        assertEquals(projectDomains.get(2).getColor(), projectEntityToListProjectDomain.get(2).getColor());
    }
}