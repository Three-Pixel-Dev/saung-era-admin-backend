package org.threepixeldev.saungeraadmin.features.user.constants;

/**
 * User Swagger message constants for use in Swagger annotations.
 * These constants use property placeholders (e.g., ${key}) to reference
 * values defined in messages.properties. This ensures the messages are
 * managed centrally in the properties file and resolved by Spring.
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
    
    public static final String GET_ALL_USERS = "${api.user.get.all.summary}";
    public static final String GET_ALL_DESCRIPTION = "${api.user.get.all.description}";
    public static final String GET_ALL_SUCCESS = "${api.user.get.all.success}";
}
