package com.rlalejandro.taskflow.project;

import org.springframework.stereotype.Service;
import com.rlalejandro.taskflow.project.dto.ProjectResponse;
import com.rlalejandro.taskflow.project.dto.CreateProjectRequest;
import com.rlalejandro.taskflow.project.dto.UpdateProjectRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectRepository projectRepository;

    public ProjectResponse create(CreateProjectRequest request) { // el metodo devuelve un DTO de respuesta
        Project project = new Project();

        project.setName(request.name());
        project.setDescription(request.description());

        Project saved = projectRepository.save(project); //.save extends of jpa repository 

        return toResponse(saved); // Project (ya con id y createdAt)  →  copiar  →  ProjectResponse
        // return toResponse(projectRepository.save(project));
    }

    public List<ProjectResponse> findAll() { 
        List<Project> projects = projectRepository.findAll(); // se obtiene la lista de proyectos
        
        List<ProjectResponse> responses = new ArrayList<>();
        for (Project project : projects) {
            responses.add(toResponse(project));
        }
        return responses;
    }

    public ProjectResponse findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found")); //findById retorna un optional
        return toResponse(project);
    }

    public ProjectResponse update(Long id, UpdateProjectRequest request) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.name());
        project.setDescription(request.description());

        return toResponse(projectRepository.save(project));
    }

    public void delete(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }

    private ProjectResponse toResponse(Project project) { 
        return new ProjectResponse(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getCreatedAt()
        );
    }
}
