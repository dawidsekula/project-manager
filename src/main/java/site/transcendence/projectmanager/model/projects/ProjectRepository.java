package site.transcendence.projectmanager.model.projects;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByOwnerUsername(String username);
    List<ProjectEntity> findAllByOwnerUsernameAndProjectStatus(String username, ProjectStatus projectStatus);
    Optional<ProjectEntity> findByUuid(String uuid);

}
