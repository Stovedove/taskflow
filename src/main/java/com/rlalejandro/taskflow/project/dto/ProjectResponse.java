package com.rlalejandro.taskflow.project.dto;

import java.time.LocalDateTime;

public record ProjectResponse( //record solo declara datos. La lógica de conversión vive en el Service.
    Long id,
    String name,
    String description,
    LocalDateTime createdAt
) {}
