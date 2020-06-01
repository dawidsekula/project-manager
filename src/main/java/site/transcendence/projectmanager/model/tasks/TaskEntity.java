package site.transcendence.projectmanager.model.tasks;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.model.projects.ProjectEntity;

import javax.persistence.*;

@Getter
@Setter

@Entity(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;
    private String name;
    private String description;
    private String category;
    @Enumerated(value = EnumType.STRING)
    private TaskStatus taskStatus;



}
