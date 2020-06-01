package site.transcendence.projectmanager.model.projects;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.users.UserDto;

@Getter
@Setter

public class ProjectDto {

    private Long id;
    private String uuid;
    private UserDto owner;
    private Long userId;
    private String name;
    private String projectTag;
    private String description;
    private ProjectStatus projectStatus;


}
