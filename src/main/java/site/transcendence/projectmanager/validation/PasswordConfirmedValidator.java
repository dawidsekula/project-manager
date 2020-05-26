package site.transcendence.projectmanager.validation;

import site.transcendence.projectmanager.model.request.CreateUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

    @Override
    public boolean isValid(Object user, ConstraintValidatorContext context) {
        String password = ((CreateUserRequest)user).getPassword();
        String confirmedPassword = ((CreateUserRequest)user).getConfirmPassword();
        return password.equals(confirmedPassword);
    }

}