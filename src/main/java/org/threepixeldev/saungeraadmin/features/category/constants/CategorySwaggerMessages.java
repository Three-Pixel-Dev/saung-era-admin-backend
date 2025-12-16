package org.threepixeldev.saungeraadmin.features.category.constants;

/**
 * Category Swagger message constants for use in Swagger annotations.
 * * These constants use property placeholders (e.g., ${key}) to reference
 * values defined in messages.properties. This ensures the messages are
 * managed centrally in the properties file and resolved by Spring.
 */
public class CategorySwaggerMessages {

    // Category Controller Tag
    public static final String TAG_NAME = "${api.category.controller.tag}";
    public static final String TAG_DESCRIPTION = "${api.category.controller.tag.description}";

    public static final String CATEGORY_REQUEST_DESCRIPTION = "${api.category.request.description}";
    public static final String CATEGORY_RESPONSE_DESCRIPTION = "${api.category.response.description}";

    public static final String CATEGORY_ID_DESCRIPTION = "${api.category.id.description}";
    public static final String CATEGORY_NAME_DESCRIPTION = "${api.category.name.description}";
    public static final String CATEGORY_DESCRIPTION_DESCRIPTION = "${api.category.description.description}";

    // Category Operations - Get All
    public static final String GET_ALL_SUMMARY = "${api.category.get.all.summary}";
    public static final String GET_ALL_DESCRIPTION = "${api.category.get.all.description}";
    public static final String GET_ALL_SUCCESS = "${api.category.get.all.success}";

    // Category Operations - Get By ID
    public static final String GET_BY_ID_SUMMARY = "${api.category.get.by.id.summary}";
    public static final String GET_BY_ID_DESCRIPTION = "${api.category.get.by.id.description}";
    public static final String GET_BY_ID_SUCCESS = "${api.category.get.by.id.success}";
    public static final String GET_BY_ID_NOT_FOUND = "${api.category.get.by.id.not.found}";
    public static final String GET_BY_ID_PARAM_ID = "${api.category.get.by.id.param.id}";

    // Category Operations - Create
    public static final String CREATE_SUMMARY = "${api.category.create.summary}";
    public static final String CREATE_DESCRIPTION = "${api.category.create.description}";
    public static final String CREATE_SUCCESS = "${api.category.create.success}";
    public static final String CREATE_BAD_REQUEST = "${api.category.create.bad.request}";
    public static final String CREATE_PARAM_REQUEST = "${api.category.create.param.request}";
    public static final String CREATE_PARAM_USER_ID = "${api.category.create.param.user.id}";

    // Category Operations - Update
    public static final String UPDATE_SUMMARY = "${api.category.update.summary}";
    public static final String UPDATE_DESCRIPTION = "${api.category.update.description}";
    public static final String UPDATE_SUCCESS = "${api.category.update.success}";
    public static final String UPDATE_BAD_REQUEST = "${api.category.update.bad.request}";
    public static final String UPDATE_NOT_FOUND = "${api.category.update.not.found}";
    public static final String UPDATE_PARAM_ID = "${api.category.update.param.id}";
    public static final String UPDATE_PARAM_REQUEST = "${api.category.update.param.request}";
    public static final String UPDATE_PARAM_USER_ID = "${api.category.update.param.user.id}";

    // Category Operations - Delete
    public static final String DELETE_SUMMARY = "${api.category.delete.summary}";
    public static final String DELETE_DESCRIPTION = "${api.category.delete.description}";
    public static final String DELETE_SUCCESS = "${api.category.delete.success}";
    public static final String DELETE_NOT_FOUND = "${api.category.delete.not.found}";
    public static final String DELETE_PARAM_ID = "${api.category.delete.param.id}";
    public static final String DELETE_PARAM_USER_ID = "${api.category.delete.param.user.id}";

    // Category Operations - Hard Delete
    public static final String HARD_DELETE_SUMMARY = "${api.category.hard.delete.summary}";
    public static final String HARD_DELETE_DESCRIPTION = "${api.category.hard.delete.description}";
    public static final String HARD_DELETE_SUCCESS = "${api.category.hard.delete.success}";
    public static final String HARD_DELETE_NOT_FOUND = "${api.category.hard.delete.not.found}";
    public static final String HARD_DELETE_PARAM_ID = "${api.category.hard.delete.param.id}";

    // Category Operations - Restore
    public static final String RESTORE_SUMMARY = "${api.category.restore.summary}";
    public static final String RESTORE_DESCRIPTION = "${api.category.restore.description}";
    public static final String RESTORE_SUCCESS = "${api.category.restore.success}";
    public static final String RESTORE_BAD_REQUEST = "${api.category.restore.bad.request}";
    public static final String RESTORE_NOT_FOUND = "${api.category.restore.not.found}";
    public static final String RESTORE_PARAM_ID = "${api.category.restore.param.id}";
    public static final String RESTORE_PARAM_USER_ID = "${api.category.restore.param.user.id}";
}
