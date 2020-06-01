package site.transcendence.projectmanager.model.tasks;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.projects.ProjectDto;

@Getter
@Setter

public class TaskDto {

    private Long id;
    private ProjectDto project;
    private String projectUuid;
    private String name;
    private String description;
    private String category;
    private TaskStatus taskStatus;

}
