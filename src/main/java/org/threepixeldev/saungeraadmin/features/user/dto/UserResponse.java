package org.threepixeldev.saungeraadmin.features.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.threepixeldev.saungeraadmin.features.user.constants.UserSwaggerMessages;
import org.threepixeldev.saungeraadmin.shared.dto.MasterData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = UserSwaggerMessages.USER_RESPONSE_DESCRIPTION)
public class UserResponse extends MasterData {
    @Schema(description = UserSwaggerMessages.USER_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = UserSwaggerMessages.USER_NAME_DESCRIPTION, example = "John Doe")
    private String name;

    @Schema(description = UserSwaggerMessages.USER_USERNAME_DESCRIPTION, example = "johndoe")
    private String username;

    @Schema(description = UserSwaggerMessages.USER_EMAIL_DESCRIPTION, example = "john.doe@example.com")
    private String email;

    @Schema(description = UserSwaggerMessages.USER_PHONE_NUMBER_DESCRIPTION, example = "+1234567890")
    private String phoneNumber;
}
