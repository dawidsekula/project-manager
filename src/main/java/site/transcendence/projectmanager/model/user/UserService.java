package site.transcendence.projectmanager.model.user;

import site.transcendence.projectmanager.model.request.CreateUserRequest;

public interface UserService {

    UserDto getUser(Long userId);
    UserDto getUser(String username);
    UserDto createUser(CreateUserRequest request);
    void deleteUser(Long userId);

    UserEntity getUserEntity(Long userId);
    UserEntity getUserEntity(String username);

}
