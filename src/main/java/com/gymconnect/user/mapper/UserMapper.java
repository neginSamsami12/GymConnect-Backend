package com.gymconnect.user.mapper;

import com.gymconnect.user.dto.UserCreateRequest;
import com.gymconnect.user.dto.UserResponse;
import com.gymconnect.user.dto.UserUpdateRequest;
import com.gymconnect.user.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserUpdateRequest request,
                      @MappingTarget User user);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}
