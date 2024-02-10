package com.cleanup.todoc.domaine.usecase

import com.cleanup.todoc.data.repository.ProjectRepository
import com.cleanup.todoc.domaine.ProjectDomainUiMapper
import com.cleanup.todoc.domaine.model.ProjectDomain
import com.cleanup.todoc.presentation.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProjectsUseCase(private val repository: ProjectRepository) {
    val allProjects: Flow<List<Project>>
        get() = repository.allProjects.map { projects: List<ProjectDomain> ->
            ProjectDomainUiMapper.mapToProjectList(
                projects
            )
        }
}
