package org.threepixeldev.saungeraadmin.features.user.dto;

import java.time.LocalDate;

import org.threepixeldev.saungeraadmin.features.user.constants.UserSwaggerMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDetails {
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
    
    @Schema(description = "Derived status based on soft-delete", example = "ACTIVE")
    private String status;
    
    private String address;

    private String kyc;

    private LocalDate dateOfBirth;

    private Integer points;

    private String referralCode;
    
    private Integer totalOrder;
}
