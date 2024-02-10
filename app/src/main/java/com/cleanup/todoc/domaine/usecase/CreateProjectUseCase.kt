package com.cleanup.todoc.domaine.usecase

import com.cleanup.todoc.data.repository.ProjectRepository
import com.cleanup.todoc.domaine.ProjectDomainUiMapper
import com.cleanup.todoc.presentation.model.Project

class CreateProjectUseCase(private val repository: ProjectRepository) {
    suspend operator fun invoke(project: Project) {
        repository.insert(ProjectDomainUiMapper.mapToDomain(project))
    }
}
