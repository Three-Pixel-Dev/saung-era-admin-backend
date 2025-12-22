package org.threepixeldev.saungeraadmin.features.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.threepixeldev.saungeraadmin.features.category.constants.CategorySwaggerMessages;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryRequest;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.features.category.service.CategoryService;
import org.threepixeldev.saungeraadmin.shared.dto.PagedResponse;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/admin/categories")
@Tag(name = CategorySwaggerMessages.TAG_NAME, description = CategorySwaggerMessages.TAG_DESCRIPTION)
public class CategoryController {

    private final CategoryService categoryService;

    private static final Set<String> ALLOWED_STATUSES = Set.of("ACTIVE", "INACTIVE", "ALL");

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = CategorySwaggerMessages.GET_ALL_SUMMARY,
            description = CategorySwaggerMessages.GET_ALL_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CategorySwaggerMessages.GET_ALL_SUCCESS,
                    content = @Content(schema = @Schema(implementation = PagedResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<?> getAllCategories(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "ACTIVE") String status,
            @RequestParam(defaultValue = "10") int size) {
        String normalizedStatus = status.toUpperCase();
        if (!ALLOWED_STATUSES.contains(normalizedStatus)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid status. Allowed values are: " + ALLOWED_STATUSES));
        }
        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<CategoryResponse> categories = categoryService.getAllCategories(keyword, status, pageable);
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = CategorySwaggerMessages.GET_BY_ID_SUMMARY,
            description = CategorySwaggerMessages.GET_BY_ID_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CategorySwaggerMessages.GET_BY_ID_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CategorySwaggerMessages.GET_BY_ID_NOT_FOUND
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = CategorySwaggerMessages.GET_BY_ID_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = CategorySwaggerMessages.CREATE_SUMMARY,
            description = CategorySwaggerMessages.CREATE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = CategorySwaggerMessages.CREATE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CategorySwaggerMessages.CREATE_BAD_REQUEST
            )
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Parameter(description = CategorySwaggerMessages.CREATE_PARAM_REQUEST, required = true)
            @Valid @RequestBody CategoryRequest request,
            @Parameter(description = CategorySwaggerMessages.CREATE_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long createdBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.createCategory(request, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Operation(
            summary = CategorySwaggerMessages.UPDATE_SUMMARY,
            description = CategorySwaggerMessages.UPDATE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CategorySwaggerMessages.UPDATE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CategorySwaggerMessages.UPDATE_BAD_REQUEST
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CategorySwaggerMessages.UPDATE_NOT_FOUND
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @Parameter(description = CategorySwaggerMessages.UPDATE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = CategorySwaggerMessages.UPDATE_PARAM_REQUEST, required = true)
            @Valid @RequestBody CategoryRequest request,
            @Parameter(description = CategorySwaggerMessages.UPDATE_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long updatedBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.updateCategory(id, request, updatedBy);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = CategorySwaggerMessages.DELETE_SUMMARY,
            description = CategorySwaggerMessages.DELETE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = CategorySwaggerMessages.DELETE_SUCCESS
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CategorySwaggerMessages.DELETE_NOT_FOUND
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = CategorySwaggerMessages.DELETE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = CategorySwaggerMessages.DELETE_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long deletedBy = userId != null ? userId : 1L;
        categoryService.deleteCategory(id, deletedBy);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = CategorySwaggerMessages.HARD_DELETE_SUMMARY,
            description = CategorySwaggerMessages.HARD_DELETE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = CategorySwaggerMessages.HARD_DELETE_SUCCESS
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CategorySwaggerMessages.HARD_DELETE_NOT_FOUND
            )
    })
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDeleteCategory(
            @Parameter(description = CategorySwaggerMessages.HARD_DELETE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id) {
        categoryService.hardDeleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = CategorySwaggerMessages.RESTORE_SUMMARY,
            description = CategorySwaggerMessages.RESTORE_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = CategorySwaggerMessages.RESTORE_SUCCESS,
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = CategorySwaggerMessages.RESTORE_BAD_REQUEST
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = CategorySwaggerMessages.RESTORE_NOT_FOUND
            )
    })
    @PostMapping("/{id}/restore")
    public ResponseEntity<CategoryResponse> restoreCategory(
            @Parameter(description = CategorySwaggerMessages.RESTORE_PARAM_ID, example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = CategorySwaggerMessages.RESTORE_PARAM_USER_ID, example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long restoredBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.restoreCategory(id, restoredBy);
        return ResponseEntity.ok(category);
    }
}
