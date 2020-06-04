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
    public TaskDto createTask(TaskDto newTask, String projectUuid) {
        TaskEntity createdTask = mapper.toEntity(newTask);
        ProjectEntity relatedProject = projectService.getProjectEntity(projectUuid);

        createdTask.setProject(relatedProject);
        TaskEntity savedTask = taskRepository.saveAndFlush(createdTask);

        String taskCode = relatedProject.getProjectTag() + savedTask.getId();
        savedTask.setCode(taskCode);

        savedTask = taskRepository.save(savedTask);

        return mapper.toDto(savedTask);
    }

    @Override
    public TaskDto getTask(String taskCode) {
        return mapper.toDto(getTaskEntity(taskCode));
    }

    @Override
    public TaskDto updateTask(String taskCode, TaskDto updatedTask) {
        TaskEntity foundTask = getTaskEntity(taskCode);
        updatedTask.setId(null);
        updatedTask.setCode(null);

        mapper.copy(updatedTask, foundTask);
        taskRepository.save(foundTask);

        return mapper.toDto(foundTask);
    }

    @Override
    public void deleteTask(String taskCode) {
        TaskEntity taskToDelete = getTaskEntity(taskCode);
        taskRepository.delete(taskToDelete);
    }

    @Override
    public TaskEntity getTaskEntity(String taskCode) {
        Optional<TaskEntity> foundTask = taskRepository.findByCode(taskCode);

        if (foundTask.isPresent()) {
            return foundTask.get();
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    public void updateTasksCode(String oldTag, String newTag) {
        List<TaskEntity> tasks = taskRepository.findAllByProjectProjectTag(oldTag);
        tasks.forEach(taskEntity -> {
            String updatedCode = taskEntity.getCode().replace(oldTag, newTag);
            taskEntity.setCode(updatedCode);
            taskRepository.save(taskEntity);
        });
    }

    @Override
    public List<TaskDto> getAllTasks(String projectUuid) {
        List<TaskEntity> tasks = taskRepository.findAllByProjectUuid(projectUuid);

        return mapper.list(tasks);
    }
}
