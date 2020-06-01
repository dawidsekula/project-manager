package site.transcendence.projectmanager.model.projects;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.users.UserEntity;

import javax.persistence.*;

@Getter
@Setter

@Entity(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    @Column(unique = true)
    private String uuid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;
    @Column(unique = true)
    private String projectTag;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

}
