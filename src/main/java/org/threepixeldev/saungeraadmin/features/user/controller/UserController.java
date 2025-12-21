package org.threepixeldev.saungeraadmin.features.user.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.features.user.constants.UserSwaggerMessages;
import org.threepixeldev.saungeraadmin.features.user.service.UserService;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponse;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponseDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@Tag(name = UserSwaggerMessages.TAG_NAME, description = UserSwaggerMessages.TAG_DESCRIPTION)
public class UserController {
	private static final Set<String> ALLOWED_STATUSES = Set.of("ACTIVE", "BLOCKED", "ALL");
	
	private final UserService userService;

	@Operation(summary = UserSwaggerMessages.GET_ALL_USERS, description = UserSwaggerMessages.GET_ALL_DESCRIPTION)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = UserSwaggerMessages.GET_ALL_SUCCESS, content = @Content(schema = @Schema(implementation = UserResponse.class))) })
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String status,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		if (status != null && !status.isEmpty()) {
	        String normalizedStatus = status.toUpperCase();
	        
	        if (!ALLOWED_STATUSES.contains(normalizedStatus)) {
	            return ResponseEntity.badRequest()
	                .body(Map.of("error", "Invalid status. Allowed values are: " + ALLOWED_STATUSES));
	        }
	        if ("ALL".equals(normalizedStatus)) {
	            status = null;
	        } else {
	            status = normalizedStatus;
	        }
	    }
		Pageable pageable = PageRequest.of(page, size);
		Map<String, Object> response = userService.getAllUsers(keyword, pageable,status);
		return ResponseEntity.ok(response);
	}
	
	@Operation(
            summary = UserSwaggerMessages.GET_BY_ID_USER,
            description = UserSwaggerMessages.GET_BY_ID_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = UserSwaggerMessages.GET_BY_ID_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = UserSwaggerMessages.GET_BY_ID_NOT_FOUND
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDetails> getUserById(
            @Parameter(description = UserSwaggerMessages.GET_BY_ID_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
		UserResponseDetails response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
	
	@Operation(
            summary = UserSwaggerMessages.BLOCK_SUMMARY,
            description = UserSwaggerMessages.BLOCK_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = UserSwaggerMessages.BLOCK_SUCCESS
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = UserSwaggerMessages.BLOCK_NOT_FOUND
            )
    })
    @DeleteMapping("/{id}/block")
    public ResponseEntity<Void> blockUser(
            @Parameter(description = UserSwaggerMessages.BLOCK_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = UserSwaggerMessages.BLOCK_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long blockedBy = userId != null ? userId : 1L;
        userService.blockUser(id, blockedBy);
        return ResponseEntity.noContent().build();
    }
	
	@Operation(
            summary = UserSwaggerMessages.UNBLOCK_SUMMARY,
            description = UserSwaggerMessages.UNBLOCK_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = UserSwaggerMessages.UNBLOCK_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = UserSwaggerMessages.UNBLOCK_BAD_REQUEST
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = UserSwaggerMessages.UNBLOCK_NOT_FOUND
            )
    })
    @PostMapping("/{id}/unblock")
    public ResponseEntity<String> unblockUser(
            @Parameter(description = UserSwaggerMessages.UNBLOCK_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = UserSwaggerMessages.UNBLOCK_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long unblockedBy = userId != null ? userId : 1L;
        String message = userService.unblockUser(id, unblockedBy);
        return ResponseEntity.ok(message);
    }
}
