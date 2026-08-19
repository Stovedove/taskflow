package com.rlalejandro.taskflow.project;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rlalejandro.taskflow.project.dto.CreateProjectRequest;
import com.rlalejandro.taskflow.project.dto.ProjectResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody CreateProjectRequest request) { // http a objeto
        ProjectResponse body = projectService.create(request);

        return ResponseEntity // método devuelve una respuesta HTTP cuyo body contiene un ProjectResponse
                .status(HttpStatus.CREATED)
                .body(body);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {
        List<ProjectResponse> body = projectService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long id) {
        ProjectResponse body = projectService.findById(id);
        return ResponseEntity.ok(body);
    }

}
