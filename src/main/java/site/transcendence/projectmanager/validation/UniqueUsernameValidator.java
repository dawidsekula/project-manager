package site.transcendence.projectmanager.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import site.transcendence.projectmanager.model.user.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Getter
@Setter

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && userRepository.findByUsername(username).isEmpty() ;
    }

}
