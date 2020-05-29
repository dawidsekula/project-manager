package site.transcendence.projectmanager.model.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import site.transcendence.projectmanager.model.user.UserEntity;
import site.transcendence.projectmanager.model.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    private ProjectMapper mapper = ProjectMapper.INSTANCE;

    @Override
    public ProjectDto createProject(ProjectDto createdDto, Principal principal) {
        UserEntity owner = userService.getUserEntity(principal.getName());

        ProjectEntity createdProject = mapper.toEntity(createdDto);
        createdProject.setOwner(owner);

        ProjectEntity savedProject = projectRepository.save(createdProject);

        return mapper.toDto(savedProject);
    }

    @Override
    public ProjectDto getProject(Long projectId, Principal principal) {
        ProjectEntity foundProject = getProjectEntity(projectId);
        CHECK_OWNERSHIP(foundProject, principal);

        return mapper.toDto(foundProject);
    }

//    @Override
//    public List<ProjectDto> getAllProjects() {
//        List<ProjectEntity> projects = toList(projectRepository.findAll());
//        return mapper.list(projects);
//    }

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
    public ProjectDto updateProject(Long projectId, ProjectDto updatedDto, Principal principal) {
        ProjectEntity updatedProject = getProjectEntity(projectId);
        CHECK_OWNERSHIP(updatedProject, principal);
        updatedDto.setId(null);

        mapper.copy(updatedDto, updatedProject);
        projectRepository.save(updatedProject);

        return mapper.toDto(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId, Principal principal) {
        ProjectEntity projectToDelete = getProjectEntity(projectId);
        CHECK_OWNERSHIP(projectToDelete,principal);

        projectRepository.delete(projectToDelete);
    }

    @Override
    public ProjectEntity getProjectEntity(Long projectId) {
        Optional<ProjectEntity> foundProject = projectRepository.findById(projectId);

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

}
