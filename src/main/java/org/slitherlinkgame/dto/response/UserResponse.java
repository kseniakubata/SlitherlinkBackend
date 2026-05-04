package org.slitherlinkgame.dto.response;



public record UserResponse(
                Long id,
                String hashPassword,
                String username,
                String email,
                String userRole
) {
}
