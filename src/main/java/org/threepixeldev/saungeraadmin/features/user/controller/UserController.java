package org.threepixeldev.saungeraadmin.features.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.threepixeldev.saungeraadmin.features.user.constants.UserSwaggerMessages;
import org.threepixeldev.saungeraadmin.features.user.service.UserService;
import org.threepixeldev.saungeraadmin.shared.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
@Tag(name = UserSwaggerMessages.TAG_NAME, description = UserSwaggerMessages.TAG_DESCRIPTION)
public class UserController {

	private final UserService userService;

	@Operation(summary = UserSwaggerMessages.GET_ALL_USERS, description = UserSwaggerMessages.GET_ALL_DESCRIPTION)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = UserSwaggerMessages.GET_ALL_SUCCESS, content = @Content(schema = @Schema(implementation = UserResponse.class))) })
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Map<String, Object> response = userService.getAllUsers(keyword, pageable);
		return ResponseEntity.ok(response);
	}
}
