package controly.backend.mappers;

import controly.backend.dto.CreateNewUserRequest;
import controly.backend.dto.SimplifiedUserResponse;
import controly.backend.dto.UpdateUsersInfoRequest;
import controly.backend.dto.UserResponse;
import controly.backend.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUsersInfoRequest dto, @MappingTarget UserEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void createUserFromDto(CreateNewUserRequest dto, @MappingTarget UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getSimplifiedDtoFromUser(UserEntity entity, @MappingTarget SimplifiedUserResponse dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getResponseDtoFromUser(UserEntity entity, @MappingTarget UserResponse dto);
}
