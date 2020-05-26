package site.transcendence.projectmanager.model.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import site.transcendence.projectmanager.model.request.CreateUserRequest;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDto source);
    UserDto toDto(UserEntity source);
    void copy(UserEntity source, @MappingTarget UserDto target);
    void copy(UserDto source, @MappingTarget UserEntity target);

    UserEntity toEntity(CreateUserRequest request);


}
