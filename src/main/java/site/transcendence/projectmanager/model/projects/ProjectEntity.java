package site.transcendence.projectmanager.model.projects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import site.transcendence.projectmanager.model.users.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter

@Entity(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    private String uuid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;
    @Size(min = 2, max = 10)
    @Column(unique = true, length = 10)
    private String projectTag;
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

}
