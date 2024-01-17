package com.cleanup.todoc.domaine.model;

import androidx.annotation.Nullable;

import java.util.List;

public class ProjectDomain {
    private final long id;
    private final String name;
    private final int color;

    public ProjectDomain(long id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Nullable
    public static ProjectDomain getProjectById(long id, List<ProjectDomain> projectDomain) {
        for (ProjectDomain project : projectDomain) {
            if (project.id == id)
                return project;
        }
        return null;
    }
}