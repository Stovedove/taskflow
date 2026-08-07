package com.rlalejandro.taskflow.project.dto;

public record CreateProjectRequest(
        String name,
        String description
) {}