package site.transcendence.projectmanager.model.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import site.transcendence.projectmanager.model.projects.ProjectMapper;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ProjectMapper.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskEntity toEntity(TaskDto source);
    @Mapping(target = "projectUuid", source = "source.project.uuid")
    TaskDto toDto(TaskEntity source);

    @Mapping(target = "projectUuid", source = "source.project.uuid")
    void copy(TaskEntity source, @MappingTarget TaskDto target);
    void copy(TaskDto source, @MappingTarget TaskEntity target);

    List<TaskDto> list(List<TaskEntity> source);

}
