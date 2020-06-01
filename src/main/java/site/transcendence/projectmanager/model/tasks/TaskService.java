package site.transcendence.projectmanager.model.tasks;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto, String projectUuid);
    TaskDto getTask(String taskName);
    TaskDto updateTask(String taskName, TaskDto updated);
    void deleteTask(String taskName);

    List<TaskDto> getAllTasks(String projectUuid);

    TaskEntity getTaskEntity(String taskName);
    void updateTasksName(String oldTag, String newTag);

}
