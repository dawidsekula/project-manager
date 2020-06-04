package site.transcendence.projectmanager.model.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

    TaskEntity saveAndFlush(TaskEntity taskEntity);
    Optional<TaskEntity> findByCode(String taskCode);
    List<TaskEntity> findAllByProjectUuid(String projectUuid);
    List<TaskEntity> findAllByProjectProjectTag(String projectTag);
    List<TaskEntity> findAllByProjectUuidAndCategory(String projectUuid, String category);
    List<TaskEntity> findAllByProjectUuidAndCategoryAndTaskStatus(String projectUuid, String category, TaskStatus taskStatus);


}
