package site.transcendence.projectmanager.model.users;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String encryptedPassword;
    private String email;

}
