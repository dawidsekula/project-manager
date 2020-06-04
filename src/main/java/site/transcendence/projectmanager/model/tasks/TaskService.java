package site.transcendence.projectmanager.model.tasks;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto newTask, String projectUuid);
    TaskDto getTask(String taskCode);
    TaskDto updateTask(String taskCode, TaskDto updatedTask);
    void deleteTask(String taskCode);

    List<TaskDto> getAllTasks(String projectUuid);

    TaskEntity getTaskEntity(String taskCode);
    void updateTasksCode(String oldTag, String newTag);

}
