package site.transcendence.projectmanager.model.users;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDto source);
    UserDto toDto(UserEntity source);
    void copy(UserEntity source, @MappingTarget UserDto target);
    void copy(UserDto source, @MappingTarget UserEntity target);

    UserEntity toEntity(CreateUserRequest request);


}
