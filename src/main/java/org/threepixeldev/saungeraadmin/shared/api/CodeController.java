package org.threepixeldev.saungeraadmin.shared.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.threepixeldev.saungeraadmin.shared.constants.CodeSwaggerMessages;
import org.threepixeldev.saungeraadmin.shared.dto.CodeDto;
import org.threepixeldev.saungeraadmin.shared.dto.CodeValueDto;
import org.threepixeldev.saungeraadmin.shared.service.CodeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/codes")
@RequiredArgsConstructor
@Tag(name = CodeSwaggerMessages.TAG_NAME, description = CodeSwaggerMessages.TAG_DESCRIPTION)
public class CodeController {

    private final CodeService codeService;

    @Operation(
            summary = CodeSwaggerMessages.GET_ALL_SUMMARY,
            description = CodeSwaggerMessages.GET_ALL_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.GET_ALL_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeDto.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<CodeDto>> getAllCodes() {
        return ResponseEntity.ok(codeService.getAllCodes());
    }

    @Operation(
            summary = CodeSwaggerMessages.GET_BY_ID_SUMMARY,
            description = CodeSwaggerMessages.GET_BY_ID_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.GET_BY_ID_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.GET_BY_ID_NOT_FOUND
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CodeDto> getCodeById(
            @Parameter(description = CodeSwaggerMessages.GET_BY_ID_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(codeService.getCodeById(id));
    }

    @Operation(
            summary = CodeSwaggerMessages.CREATE_SUMMARY,
            description = CodeSwaggerMessages.CREATE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = CodeSwaggerMessages.CREATE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CodeSwaggerMessages.CREATE_BAD_REQUEST
            )
    })
    @PostMapping
    public ResponseEntity<CodeDto> createCode(
            @Parameter(description = CodeSwaggerMessages.CREATE_PARAM_REQUEST, required = true)
            @RequestBody CodeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codeService.createCode(dto));
    }

    @Operation(
            summary = CodeSwaggerMessages.UPDATE_SUMMARY,
            description = CodeSwaggerMessages.UPDATE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.UPDATE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CodeSwaggerMessages.UPDATE_BAD_REQUEST
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.UPDATE_NOT_FOUND
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CodeDto> updateCode(
            @Parameter(description = CodeSwaggerMessages.UPDATE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = CodeSwaggerMessages.UPDATE_PARAM_REQUEST, required = true)
            @RequestBody CodeDto dto) {
        return ResponseEntity.ok(codeService.updateCode(id, dto));
    }

    @Operation(
            summary = CodeSwaggerMessages.DELETE_SUMMARY,
            description = CodeSwaggerMessages.DELETE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.DELETE_SUCCESS
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.DELETE_NOT_FOUND
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCode(
            @Parameter(description = CodeSwaggerMessages.DELETE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
        codeService.deleteCode(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = CodeSwaggerMessages.GET_VALUES_BY_CODE_SUMMARY,
            description = CodeSwaggerMessages.GET_VALUES_BY_CODE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.GET_VALUES_BY_CODE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeValueDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.GET_VALUES_BY_CODE_NOT_FOUND
            )
    })
    @GetMapping("/{codeId}/values")
    public ResponseEntity<List<CodeValueDto>> getValuesByCode(
            @Parameter(description = CodeSwaggerMessages.GET_VALUES_BY_CODE_PARAM_CODE_ID, example = "1", required = true)
            @PathVariable Long codeId) {
        return ResponseEntity.ok(codeService.getValuesByCodeId(codeId));
    }

    @Operation(
            summary = CodeSwaggerMessages.CREATE_VALUE_SUMMARY,
            description = CodeSwaggerMessages.CREATE_VALUE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = CodeSwaggerMessages.CREATE_VALUE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeValueDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CodeSwaggerMessages.CREATE_VALUE_BAD_REQUEST
            )
    })
    @PostMapping("/values")
    public ResponseEntity<CodeValueDto> createCodeValue(
            @Parameter(description = CodeSwaggerMessages.CREATE_VALUE_PARAM_REQUEST, required = true)
            @RequestBody CodeValueDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codeService.createCodeValue(dto));
    }

    @Operation(
            summary = CodeSwaggerMessages.UPDATE_VALUE_SUMMARY,
            description = CodeSwaggerMessages.UPDATE_VALUE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.UPDATE_VALUE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CodeValueDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CodeSwaggerMessages.UPDATE_VALUE_BAD_REQUEST
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.UPDATE_VALUE_NOT_FOUND
            )
    })
    @PutMapping("/values/{id}")
    public ResponseEntity<CodeValueDto> updateCodeValue(
            @Parameter(description = CodeSwaggerMessages.UPDATE_VALUE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = CodeSwaggerMessages.UPDATE_VALUE_PARAM_REQUEST, required = true)
            @RequestBody CodeValueDto dto) {
        return ResponseEntity.ok(codeService.updateCodeValue(id, dto));
    }

    @Operation(
            summary = CodeSwaggerMessages.DELETE_VALUE_SUMMARY,
            description = CodeSwaggerMessages.DELETE_VALUE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CodeSwaggerMessages.DELETE_VALUE_SUCCESS
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CodeSwaggerMessages.DELETE_VALUE_NOT_FOUND
            )
    })
    @DeleteMapping("/values/{id}")
    public ResponseEntity<Void> deleteCodeValue(
            @Parameter(description = CodeSwaggerMessages.DELETE_VALUE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
        codeService.deleteCodeValue(id);
        return ResponseEntity.ok().build();
    }
}
