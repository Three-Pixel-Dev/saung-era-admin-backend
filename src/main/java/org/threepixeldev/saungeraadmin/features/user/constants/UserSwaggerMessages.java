package org.threepixeldev.saungeraadmin.features.user.constants;

/**
 * User Swagger message constants for use in Swagger annotations. These
 * constants use property placeholders (e.g., ${key}) to reference values
 * defined in messages.properties. This ensures the messages are managed
 * centrally in the properties file and resolved by Spring.
 */
public class UserSwaggerMessages {
	// User Controller Tag
	public static final String TAG_NAME = "${api.user.controller.tag}";
	public static final String TAG_DESCRIPTION = "${api.user.controller.tag.description}";

	public static final String USER_REQUEST_DESCRIPTION = "${api.user.description}";
	public static final String USER_RESPONSE_DESCRIPTION = "${api.user.response.description}";

	public static final String USER_ID_DESCRIPTION = "${api.user.id.description}";
	public static final String USER_NAME_DESCRIPTION = "${api.user.name.description}";
	public static final String USER_USERNAME_DESCRIPTION = "${api.user.username.description}";
	public static final String USER_EMAIL_DESCRIPTION = "${api.user.email.description}";
	public static final String USER_PHONE_NUMBER_DESCRIPTION = "${api.user.phone.number.description}";
	public static final String USER_PASSWORD_DESCRIPTION = "${api.user.password.description}";

	// User Operations - Get All
	public static final String GET_ALL_USERS = "${api.user.get.all.summary}";
	public static final String GET_ALL_DESCRIPTION = "${api.user.get.all.description}";
	public static final String GET_ALL_SUCCESS = "${api.user.get.all.success}";

	// User Operations - Get By ID
	public static final String GET_BY_ID_USER = "${api.user.get.by.id.summary}";
	public static final String GET_BY_ID_DESCRIPTION = "${api.user.get.by.id.description}";
	public static final String GET_BY_ID_SUCCESS = "${api.user.get.by.id.success}";
	public static final String GET_BY_ID_NOT_FOUND = "${api.user.get.by.id.not.found}";
	public static final String GET_BY_ID_PARAM_ID = "${api.user.get.by.id.param.id}";

	// User Operations - BLOCK
	public static final String BLOCK_SUMMARY = "${api.user.block.summary}";
	public static final String BLOCK_DESCRIPTION = "${api.user.block.description}";
	public static final String BLOCK_SUCCESS = "${api.user.block.success}";
	public static final String BLOCK_NOT_FOUND = "${api.user.block.not.found}";
	public static final String BLOCK_PARAM_ID = "${api.user.block.param.id}";
	public static final String BLOCK_PARAM_USER_ID = "${api.user.block.param.user.id}";

	// User Operations - UNBLOCK
	public static final String UNBLOCK_SUMMARY = "${api.user.unblock.summary}";
	public static final String UNBLOCK_DESCRIPTION = "${api.user.unblock.description}";
	public static final String UNBLOCK_SUCCESS = "${api.user.unblock.success}";
	public static final String UNBLOCK_BAD_REQUEST = "${api.user.unblock.bad.request}";
	public static final String UNBLOCK_NOT_FOUND = "${api.user.unblock.not.found}";
	public static final String UNBLOCK_PARAM_ID = "${api.user.unblock.param.id}";
	public static final String UNBLOCK_PARAM_USER_ID = "${api.user.unblock.param.user.id}";
}
