package site.transcendence.projectmanager.model.project;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.user.UserDto;

@Getter
@Setter

public class ProjectDto {

    private Long id;
    private UserDto owner;
    private Long userId;
    private String description;
    private ProjectStatus projectStatus;


}
