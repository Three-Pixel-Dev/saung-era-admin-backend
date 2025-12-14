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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryRequest;
import org.threepixeldev.saungeraadmin.features.category.dto.CategoryResponse;
import org.threepixeldev.saungeraadmin.features.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@Tag(name = "Category Management", description = "APIs for managing product categories. " +
        "Categories are cached in Redis with a 30-minute TTL for improved performance.")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Get all categories",
            description = "Retrieves a list of all active (non-deleted) categories. " +
                    "Results are cached in Redis for 30 minutes to improve performance. " +
                    "The cache is automatically invalidated when categories are created, updated, or deleted."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of categories",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Get category by ID",
            description = "Retrieves a specific category by its unique identifier. " +
                    "Only active (non-deleted) categories can be retrieved. " +
                    "Results are cached in Redis for 30 minutes."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category found and returned successfully",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found or has been deleted"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "Unique identifier of the category", example = "1", required = true)
            @PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new product category. " +
                    "The category name must be unique among active categories. " +
                    "This operation automatically clears the category cache to ensure fresh data on next retrieval."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Category created successfully",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data or category name already exists"
            )
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Parameter(description = "Category creation request", required = true)
            @Valid @RequestBody CategoryRequest request,
            @Parameter(description = "ID of the user creating the category (optional, defaults to 1)", example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long createdBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.createCategory(request, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Operation(
            summary = "Update an existing category",
            description = "Updates an existing active category. " +
                    "The category name must be unique among active categories (excluding the current category). " +
                    "This operation automatically clears the category cache."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category updated successfully",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data or category name already exists"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found or has been deleted"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @Parameter(description = "Unique identifier of the category to update", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Category update request", required = true)
            @Valid @RequestBody CategoryRequest request,
            @Parameter(description = "ID of the user updating the category (optional, defaults to 1)", example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long updatedBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.updateCategory(id, request, updatedBy);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = "Soft delete a category",
            description = "Performs a soft delete on a category by setting the deletedAt timestamp. " +
                    "The category is not permanently removed from the database and can be restored later. " +
                    "This operation automatically clears the category cache."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Category soft deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found or already deleted"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "Unique identifier of the category to delete", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the user deleting the category (optional, defaults to 1)", example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long deletedBy = userId != null ? userId : 1L;
        categoryService.deleteCategory(id, deletedBy);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Hard delete a category",
            description = "Permanently deletes a category from the database. " +
                    "This operation cannot be undone. Use soft delete if you want the option to restore the category later. " +
                    "This operation automatically clears the category cache."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Category permanently deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found"
            )
    })
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDeleteCategory(
            @Parameter(description = "Unique identifier of the category to permanently delete", example = "1", required = true)
            @PathVariable Long id) {
        categoryService.hardDeleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Restore a deleted category",
            description = "Restores a soft-deleted category by clearing the deletedAt timestamp. " +
                    "The category name must be unique among active categories. " +
                    "If an active category with the same name exists, the restore operation will fail. " +
                    "This operation automatically clears the category cache."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category restored successfully",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cannot restore: An active category with the same name already exists"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Deleted category not found with the specified ID"
            )
    })
    @PostMapping("/{id}/restore")
    public ResponseEntity<CategoryResponse> restoreCategory(
            @Parameter(description = "Unique identifier of the deleted category to restore", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the user restoring the category (optional, defaults to 1)", example = "1")
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Long restoredBy = userId != null ? userId : 1L;
        CategoryResponse category = categoryService.restoreCategory(id, restoredBy);
        return ResponseEntity.ok(category);
    }
}
