package site.transcendence.projectmanager.model.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.transcendence.projectmanager.model.projects.ProjectEntity;
import site.transcendence.projectmanager.model.projects.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectService projectService;

    private TaskMapper mapper = TaskMapper.INSTANCE;

    @Override
    public TaskDto createTask(TaskDto taskDto, String projectUuid) {
        TaskEntity createdTask = mapper.toEntity(taskDto);
        ProjectEntity relatedProject = projectService.getProjectEntity(projectUuid);

        createdTask.setProject(relatedProject);
        TaskEntity savedTask = taskRepository.saveAndFlush(createdTask);

        String taskName = relatedProject.getProjectTag() + savedTask.getId();
        savedTask.setName(taskName);

        savedTask = taskRepository.save(savedTask);

        return mapper.toDto(savedTask);
    }

    @Override
    public TaskDto getTask(String taskName) {
        return mapper.toDto(getTaskEntity(taskName));
    }

    @Override
    public TaskDto updateTask(String taskName, TaskDto updated) {
        TaskEntity foundTask = getTaskEntity(taskName);
        updated.setId(null);
        updated.setName(null);

        mapper.copy(updated, foundTask);
        taskRepository.save(foundTask);

        return mapper.toDto(foundTask);
    }

    @Override
    public void deleteTask(String taskName) {
        TaskEntity taskToDelete = getTaskEntity(taskName);
        taskRepository.delete(taskToDelete);
    }

    @Override
    public TaskEntity getTaskEntity(String taskName) {
        Optional<TaskEntity> foundTask = taskRepository.findByName(taskName);

        if (foundTask.isPresent()) {
            return foundTask.get();
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    public void updateTasksName(String oldTag, String newTag) {
        List<TaskEntity> tasks = taskRepository.findAllByProjectProjectTag(oldTag);
        tasks.forEach(taskEntity -> {
            String updatedName = taskEntity.getName().replace(oldTag, newTag);
            taskEntity.setName(updatedName);
            taskRepository.save(taskEntity);
        });
    }

    @Override
    public List<TaskDto> getAllTasks(String projectUuid) {
        List<TaskEntity> tasks = taskRepository.findAllByProjectUuid(projectUuid);

        return mapper.list(tasks);
    }
}
