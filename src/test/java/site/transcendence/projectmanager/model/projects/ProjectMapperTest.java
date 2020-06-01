package site.transcendence.projectmanager.model.projects;

import org.junit.jupiter.api.Test;
import site.transcendence.projectmanager.model.users.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMapperTest {

    @Test
    void toDtoWithSettingEntityId() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(10L);
        userEntity.setUsername("username");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setDescription("TEST");
        projectEntity.setOwner(userEntity);

        ProjectDto projectDto = ProjectMapper.INSTANCE.toDto(projectEntity);

        assertEquals(10L, projectDto.getUserId());
    }

    @Test
    void toDtoNotNull() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setDescription("TEST");
        projectEntity.setOwner(userEntity);

        ProjectDto projectDto = ProjectMapper.INSTANCE.toDto(projectEntity);

        assertNotNull(projectDto.getOwner());
        assertEquals("username", projectDto.getOwner().getUsername());
        assertEquals("TEST", projectDto.getDescription());
    }
}