package site.transcendence.projectmanager.model.project;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.user.UserEntity;

import javax.persistence.*;

@Getter
@Setter

@Entity(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

}
