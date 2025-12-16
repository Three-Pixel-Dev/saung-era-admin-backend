package org.threepixeldev.saungeraadmin.features.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threepixeldev.saungeraadmin.features.user.constants.UserSwaggerMessages;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = UserSwaggerMessages.USER_REQUEST_DESCRIPTION)
public class UserRequest {
    @NotBlank(message = "{validation.user.name.required}")
    @Schema(description = UserSwaggerMessages.USER_NAME_DESCRIPTION, example = "John Doe", maxLength = 255)
    private String name;

    @NotBlank(message = "{validation.user.username.required}")
    @Schema(description = UserSwaggerMessages.USER_USERNAME_DESCRIPTION, example = "johndoe", maxLength = 100)
    private String username;

    @NotBlank(message = "{validation.user.email.required}")
    @Email(message = "{validation.user.email.invalid}")
    @Schema(description = UserSwaggerMessages.USER_EMAIL_DESCRIPTION, example = "john.doe@example.com", maxLength = 255)
    private String email;

    @Schema(description = UserSwaggerMessages.USER_PHONE_NUMBER_DESCRIPTION, example = "+1234567890", maxLength = 50)
    private String phoneNumber;

    @Schema(description = UserSwaggerMessages.USER_PASSWORD_DESCRIPTION, example = "SecurePassword123!", maxLength = 255)
    private String password;
}
