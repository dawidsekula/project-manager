package site.transcendence.projectmanager.model.project;

import org.springframework.data.repository.CrudRepository;
import site.transcendence.projectmanager.model.user.UserEntity;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByOwner(UserEntity user);
    List<ProjectEntity> findAllByOwnerUsername(String username);
    List<ProjectEntity> findAllByOwnerUsernameAndProjectStatus(String username, ProjectStatus projectStatus);

}
