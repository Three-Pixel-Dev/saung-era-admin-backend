package org.threepixeldev.saungeraadmin.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threepixeldev.saungeraadmin.shared.constants.CodeSwaggerMessages;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = CodeSwaggerMessages.CODE_VALUE_RESPONSE_DESCRIPTION)
public class CodeValueDto {
    @Schema(description = CodeSwaggerMessages.CODE_VALUE_ID_DESCRIPTION, example = "1")
    private Long id;

    @Schema(description = CodeSwaggerMessages.CODE_VALUE_CODE_ID_DESCRIPTION, example = "1")
    private Long codeId;

    @Schema(description = CodeSwaggerMessages.CODE_VALUE_NAME_DESCRIPTION, example = "PENDING", maxLength = 255)
    private String name;

    @Schema(description = CodeSwaggerMessages.CODE_VALUE_DESCRIPTION_DESCRIPTION, example = "Order is pending", maxLength = 1000)
    private String description;
}
