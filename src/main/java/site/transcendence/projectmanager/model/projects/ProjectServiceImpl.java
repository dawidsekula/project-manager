package site.transcendence.projectmanager.model.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.transcendence.projectmanager.model.tasks.TaskService;
import site.transcendence.projectmanager.model.users.UserEntity;
import site.transcendence.projectmanager.model.users.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    private ProjectMapper mapper = ProjectMapper.INSTANCE;

    @Override
    public ProjectDto createProject(ProjectDto createdDto, Principal principal) {
        UserEntity owner = userService.getUserEntity(principal.getName());
        createdDto.setId(null);
        createdDto.setUuid(null);
        createdDto.setOwner(null);
        createdDto.setUserId(null);
        createdDto.setProjectTag(createdDto.getProjectTag().toUpperCase());

        ProjectEntity createdProject = mapper.toEntity(createdDto);
        createdProject.setOwner(owner);
        createdProject.setUuid(UUID.randomUUID().toString());

        // TODO: Validation handling
        ProjectEntity savedProject = projectRepository.save(createdProject);

        return mapper.toDto(savedProject);
    }

    @Override
    public ProjectDto getProject(String uuid, Principal principal) {
        ProjectEntity foundProject = getProjectEntity(uuid);
        CHECK_OWNERSHIP(foundProject, principal);

        return mapper.toDto(foundProject);
    }

    @Override
    public List<ProjectDto> getAllProjects(Principal principal) {
        List<ProjectEntity> projects = toList(projectRepository.findAllByOwnerUsername(principal.getName()));
        return mapper.list(projects);
    }

    @Override
    public List<ProjectDto> getAllProjects(Principal principal, ProjectStatus projectStatus) {
        List<ProjectEntity> projects = toList(projectRepository.findAllByOwnerUsernameAndProjectStatus(principal.getName(), projectStatus));
        return mapper.list(projects);
    }

    @Override
    @Transactional
    public ProjectDto updateProject(String uuid, ProjectDto updatedDto, Principal principal) {
        ProjectEntity updatedProject = getProjectEntity(uuid);
        CHECK_OWNERSHIP(updatedProject, principal);
        updatedDto.setId(null);
        updatedDto.setUuid(null);

        if (!updatedProject.getProjectTag().equalsIgnoreCase(updatedDto.getProjectTag()) && updatedDto.getProjectTag() != null){
            updateProjectTag(updatedProject, updatedDto.getProjectTag());
        }

        mapper.copy(updatedDto, updatedProject);
        projectRepository.save(updatedProject);

        return mapper.toDto(updatedProject);
    }

    @Override
    public void deleteProject(String uuid, Principal principal) {
        ProjectEntity projectToDelete = getProjectEntity(uuid);
        CHECK_OWNERSHIP(projectToDelete,principal);

        projectRepository.delete(projectToDelete);
    }

    @Override
    public ProjectEntity getProjectEntity(String uuid) {
        Optional<ProjectEntity> foundProject = projectRepository.findByUuid(uuid);

        if (foundProject.isPresent()) {
            return foundProject.get();
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    private static <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private static void CHECK_OWNERSHIP(ProjectEntity project, Principal principal) {
        if (!project.getOwner().getUsername().equalsIgnoreCase(principal.getName())) {
            throw new RuntimeException("User is not the owner");
        }
    }

    @Override
    @Transactional
    public void updateProjectTag(ProjectEntity project, String newTag) {
        taskService.updateTasksCode(project.getProjectTag(), newTag);
        project.setProjectTag(newTag);
    }
}
