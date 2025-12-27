package org.threepixeldev.saungeraadmin.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threepixeldev.saungeraadmin.shared.constants.CodeSwaggerMessages;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = CodeSwaggerMessages.CODE_RESPONSE_DESCRIPTION)
public class CodeDto {
    @Schema(description = CodeSwaggerMessages.CODE_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = CodeSwaggerMessages.CODE_NAME_DESCRIPTION, example = "STATUS", maxLength = 255)
    private String name;

    @Schema(description = CodeSwaggerMessages.CODE_DESCRIPTION_DESCRIPTION, example = "Status code for order management", maxLength = 1000)
    private String description;
}
