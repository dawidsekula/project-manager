package site.transcendence.projectmanager.model.project;

import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;

public interface ProjectService {

    // Create
    ProjectDto createProject(ProjectDto createdDto, Principal principal);

    // Read
    ProjectDto getProject(Long projectId, Principal principal);
    List<ProjectDto> getAllProjects(Principal principal);
    List<ProjectDto> getAllProjects(Principal principal, ProjectStatus projectStatus);

    // Update
    ProjectDto updateProject(Long projectId, ProjectDto updatedDto, Principal principal);

    // Delete
    void deleteProject(Long projectId, Principal principal);

    // Utils
    ProjectEntity getProjectEntity(Long projectId);



}
