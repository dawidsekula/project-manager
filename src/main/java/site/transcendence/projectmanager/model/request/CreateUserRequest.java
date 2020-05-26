package site.transcendence.projectmanager.model.request;

import lombok.Getter;
import lombok.Setter;
import site.transcendence.projectmanager.validation.PasswordConfirmed;
import site.transcendence.projectmanager.validation.PasswordPolicy;
import site.transcendence.projectmanager.validation.UniqueEmail;
import site.transcendence.projectmanager.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter

@PasswordConfirmed
public class CreateUserRequest {

    @NotBlank(message = "Please enter the username")
    @UniqueUsername
    private String username;
    @NotBlank(message = "Please enter the password")
    @PasswordPolicy
    private String password;
    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;
    @NotBlank(message = "Please enter your email address")
    @Email
    @UniqueEmail
    private String email;

}
