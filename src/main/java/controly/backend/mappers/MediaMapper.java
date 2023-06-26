package controly.backend.mappers;

import controly.backend.dto.*;
import controly.backend.entities.MediaDataEntity;
import controly.backend.entities.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getResponseDtoFromMedia(MediaDataEntity entity, @MappingTarget MediaFileResponse dto);
}
