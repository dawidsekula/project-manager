package site.transcendence.projectmanager.model.projects;

import java.security.Principal;
import java.util.List;

public interface ProjectService {

    // Create
    ProjectDto createProject(ProjectDto createdDto, Principal principal);

    // Read
    ProjectDto getProject(String uuid, Principal principal);
    List<ProjectDto> getAllProjects(Principal principal);
    List<ProjectDto> getAllProjects(Principal principal, ProjectStatus projectStatus);

    // Update
    ProjectDto updateProject(String uuid, ProjectDto updatedDto, Principal principal);
    void updateProjectTag(ProjectEntity project, String newTag);

    // Delete
    void deleteProject(String uuid, Principal principal);

    // Utils
    ProjectEntity getProjectEntity(String uuid);



}
