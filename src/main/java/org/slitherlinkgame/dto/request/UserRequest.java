package org.slitherlinkgame.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
                @NotEmpty String hashPassword,
                @NotEmpty String username,
                @NotEmpty @Email String email,
                @NotEmpty String userRole
        ) {
}
