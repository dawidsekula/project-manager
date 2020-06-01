package site.transcendence.projectmanager.model.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserMapper mapper = UserMapper.INSTANCE;

    @Override
    public UserDto getUser(Long userId) {
        UserEntity foundUser = getUserEntity(userId);
        return mapper.toDto(foundUser);
    }

    @Override
    public UserDto getUser(String username) {
        UserEntity foundUser = getUserEntity(username);
        return mapper.toDto(foundUser);
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {
        UserEntity createdUser = mapper.toEntity(request);
        createdUser.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepository.save(createdUser);

        return mapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userToDelete = getUserEntity(userId);
        userRepository.delete(userToDelete);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity foundUser = getUserEntity(username);

        return User
                .withUsername(foundUser.getUsername())
                .password(foundUser.getEncryptedPassword())
                .roles("USER")
                .build();
    }

    @Override
    public UserEntity getUserEntity(Long userId){
        Optional<UserEntity> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()){
            return foundUser.get();
        }else{
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserEntity getUserEntity(String username){
        Optional<UserEntity> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()){
            return foundUser.get();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

}
