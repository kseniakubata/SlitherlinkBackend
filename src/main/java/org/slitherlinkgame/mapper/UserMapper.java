package org.slitherlinkgame.mapper;

import org.mapstruct.Mapper;
import org.slitherlinkgame.dto.request.UserRequest;
import org.slitherlinkgame.dto.response.UserResponse;
import org.slitherlinkgame.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromRequest(UserRequest userRequest);
    UserResponse toResponse(User user);

}
