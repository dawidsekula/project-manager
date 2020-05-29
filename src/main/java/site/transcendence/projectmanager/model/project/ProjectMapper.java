package site.transcendence.projectmanager.model.project;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import site.transcendence.projectmanager.model.user.UserMapper;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserMapper.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectEntity toEntity(ProjectDto source);
    @Mapping(target = "userId", source = "source.owner.id")
    ProjectDto toDto(ProjectEntity source);

    @Mapping(target = "userId", source = "source.owner.id")
    void copy(ProjectEntity source, @MappingTarget ProjectDto target);
    void copy(ProjectDto source, @MappingTarget ProjectEntity target);

    List<ProjectDto> list(List<ProjectEntity> source);

}
