package org.threepixeldev.saungeraadmin.shared.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * Constants class for API messages that can be used in Swagger annotations.
 * Values are loaded from messages.properties at initialization.
 * 
 * Note: Since Swagger annotations require compile-time constants, we use
 * static final String fields. These are initialized from MessageSource at startup.
 */
@Component
public class ApiConstants {
    
    private static MessageSource staticMessageSource;
    
    @Autowired
    private MessageSource messageSource;
    
    @PostConstruct
    public void init() {
        staticMessageSource = messageSource;
    }
    
    // Helper method to get message
    private static String getMessage(String key) {
        if (staticMessageSource == null) {
            return key; // Fallback if not initialized
        }
        return staticMessageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
    }
    
    // Category API Constants
    public static final String CATEGORY_DESCRIPTION = "api.category.description";
    public static final String CATEGORY_RESPONSE_DESCRIPTION = "api.category.response.description";
    public static final String CATEGORY_ID_DESCRIPTION = "api.category.id.description";
    public static final String CATEGORY_NAME_DESCRIPTION = "api.category.name.description";
    public static final String CATEGORY_DESCRIPTION_DESCRIPTION = "api.category.description.description";
    
    // For use in code (runtime)
    public static String getCategoryDescription() { return getMessage(CATEGORY_DESCRIPTION); }
    public static String getCategoryResponseDescription() { return getMessage(CATEGORY_RESPONSE_DESCRIPTION); }
    public static String getCategoryIdDescription() { return getMessage(CATEGORY_ID_DESCRIPTION); }
    public static String getCategoryNameDescription() { return getMessage(CATEGORY_NAME_DESCRIPTION); }
    public static String getCategoryDescriptionDescription() { return getMessage(CATEGORY_DESCRIPTION_DESCRIPTION); }
    
    // Category Operations
    public static final String CATEGORY_GET_ALL_SUMMARY = "api.category.get.all.summary";
    public static final String CATEGORY_GET_ALL_DESCRIPTION = "api.category.get.all.description";
    public static final String CATEGORY_GET_ALL_SUCCESS = "api.category.get.all.success";
    
    public static String getCategoryGetAllSummary() { return getMessage(CATEGORY_GET_ALL_SUMMARY); }
    public static String getCategoryGetAllDescription() { return getMessage(CATEGORY_GET_ALL_DESCRIPTION); }
    public static String getCategoryGetAllSuccess() { return getMessage(CATEGORY_GET_ALL_SUCCESS); }
    
    public static final String CATEGORY_GET_BY_ID_SUMMARY = "api.category.get.by.id.summary";
    public static final String CATEGORY_GET_BY_ID_DESCRIPTION = "api.category.get.by.id.description";
    public static final String CATEGORY_GET_BY_ID_SUCCESS = "api.category.get.by.id.success";
    public static final String CATEGORY_GET_BY_ID_NOT_FOUND = "api.category.get.by.id.not.found";
    public static final String CATEGORY_GET_BY_ID_PARAM_ID = "api.category.get.by.id.param.id";
    
    public static String getCategoryGetByIdSummary() { return getMessage(CATEGORY_GET_BY_ID_SUMMARY); }
    public static String getCategoryGetByIdDescription() { return getMessage(CATEGORY_GET_BY_ID_DESCRIPTION); }
    public static String getCategoryGetByIdSuccess() { return getMessage(CATEGORY_GET_BY_ID_SUCCESS); }
    public static String getCategoryGetByIdNotFound() { return getMessage(CATEGORY_GET_BY_ID_NOT_FOUND); }
    public static String getCategoryGetByIdParamId() { return getMessage(CATEGORY_GET_BY_ID_PARAM_ID); }
    
    public static final String CATEGORY_CREATE_SUMMARY = "api.category.create.summary";
    public static final String CATEGORY_CREATE_DESCRIPTION = "api.category.create.description";
    public static final String CATEGORY_CREATE_SUCCESS = "api.category.create.success";
    public static final String CATEGORY_CREATE_BAD_REQUEST = "api.category.create.bad.request";
    public static final String CATEGORY_CREATE_PARAM_REQUEST = "api.category.create.param.request";
    public static final String CATEGORY_CREATE_PARAM_USER_ID = "api.category.create.param.user.id";
    
    public static String getCategoryCreateSummary() { return getMessage(CATEGORY_CREATE_SUMMARY); }
    public static String getCategoryCreateDescription() { return getMessage(CATEGORY_CREATE_DESCRIPTION); }
    public static String getCategoryCreateSuccess() { return getMessage(CATEGORY_CREATE_SUCCESS); }
    public static String getCategoryCreateBadRequest() { return getMessage(CATEGORY_CREATE_BAD_REQUEST); }
    public static String getCategoryCreateParamRequest() { return getMessage(CATEGORY_CREATE_PARAM_REQUEST); }
    public static String getCategoryCreateParamUserId() { return getMessage(CATEGORY_CREATE_PARAM_USER_ID); }
    
    public static final String CATEGORY_UPDATE_SUMMARY = "api.category.update.summary";
    public static final String CATEGORY_UPDATE_DESCRIPTION = "api.category.update.description";
    public static final String CATEGORY_UPDATE_SUCCESS = "api.category.update.success";
    public static final String CATEGORY_UPDATE_BAD_REQUEST = "api.category.update.bad.request";
    public static final String CATEGORY_UPDATE_NOT_FOUND = "api.category.update.not.found";
    public static final String CATEGORY_UPDATE_PARAM_ID = "api.category.update.param.id";
    public static final String CATEGORY_UPDATE_PARAM_REQUEST = "api.category.update.param.request";
    public static final String CATEGORY_UPDATE_PARAM_USER_ID = "api.category.update.param.user.id";
    
    public static String getCategoryUpdateSummary() { return getMessage(CATEGORY_UPDATE_SUMMARY); }
    public static String getCategoryUpdateDescription() { return getMessage(CATEGORY_UPDATE_DESCRIPTION); }
    public static String getCategoryUpdateSuccess() { return getMessage(CATEGORY_UPDATE_SUCCESS); }
    public static String getCategoryUpdateBadRequest() { return getMessage(CATEGORY_UPDATE_BAD_REQUEST); }
    public static String getCategoryUpdateNotFound() { return getMessage(CATEGORY_UPDATE_NOT_FOUND); }
    public static String getCategoryUpdateParamId() { return getMessage(CATEGORY_UPDATE_PARAM_ID); }
    public static String getCategoryUpdateParamRequest() { return getMessage(CATEGORY_UPDATE_PARAM_REQUEST); }
    public static String getCategoryUpdateParamUserId() { return getMessage(CATEGORY_UPDATE_PARAM_USER_ID); }
    
    public static final String CATEGORY_DELETE_SUMMARY = "api.category.delete.summary";
    public static final String CATEGORY_DELETE_DESCRIPTION = "api.category.delete.description";
    public static final String CATEGORY_DELETE_SUCCESS = "api.category.delete.success";
    public static final String CATEGORY_DELETE_NOT_FOUND = "api.category.delete.not.found";
    public static final String CATEGORY_DELETE_PARAM_ID = "api.category.delete.param.id";
    public static final String CATEGORY_DELETE_PARAM_USER_ID = "api.category.delete.param.user.id";
    
    public static String getCategoryDeleteSummary() { return getMessage(CATEGORY_DELETE_SUMMARY); }
    public static String getCategoryDeleteDescription() { return getMessage(CATEGORY_DELETE_DESCRIPTION); }
    public static String getCategoryDeleteSuccess() { return getMessage(CATEGORY_DELETE_SUCCESS); }
    public static String getCategoryDeleteNotFound() { return getMessage(CATEGORY_DELETE_NOT_FOUND); }
    public static String getCategoryDeleteParamId() { return getMessage(CATEGORY_DELETE_PARAM_ID); }
    public static String getCategoryDeleteParamUserId() { return getMessage(CATEGORY_DELETE_PARAM_USER_ID); }
    
    public static final String CATEGORY_HARD_DELETE_SUMMARY = "api.category.hard.delete.summary";
    public static final String CATEGORY_HARD_DELETE_DESCRIPTION = "api.category.hard.delete.description";
    public static final String CATEGORY_HARD_DELETE_SUCCESS = "api.category.hard.delete.success";
    public static final String CATEGORY_HARD_DELETE_NOT_FOUND = "api.category.hard.delete.not.found";
    public static final String CATEGORY_HARD_DELETE_PARAM_ID = "api.category.hard.delete.param.id";
    
    public static String getCategoryHardDeleteSummary() { return getMessage(CATEGORY_HARD_DELETE_SUMMARY); }
    public static String getCategoryHardDeleteDescription() { return getMessage(CATEGORY_HARD_DELETE_DESCRIPTION); }
    public static String getCategoryHardDeleteSuccess() { return getMessage(CATEGORY_HARD_DELETE_SUCCESS); }
    public static String getCategoryHardDeleteNotFound() { return getMessage(CATEGORY_HARD_DELETE_NOT_FOUND); }
    public static String getCategoryHardDeleteParamId() { return getMessage(CATEGORY_HARD_DELETE_PARAM_ID); }
    
    public static final String CATEGORY_RESTORE_SUMMARY = "api.category.restore.summary";
    public static final String CATEGORY_RESTORE_DESCRIPTION = "api.category.restore.description";
    public static final String CATEGORY_RESTORE_SUCCESS = "api.category.restore.success";
    public static final String CATEGORY_RESTORE_BAD_REQUEST = "api.category.restore.bad.request";
    public static final String CATEGORY_RESTORE_NOT_FOUND = "api.category.restore.not.found";
    public static final String CATEGORY_RESTORE_PARAM_ID = "api.category.restore.param.id";
    public static final String CATEGORY_RESTORE_PARAM_USER_ID = "api.category.restore.param.user.id";
    
    public static String getCategoryRestoreSummary() { return getMessage(CATEGORY_RESTORE_SUMMARY); }
    public static String getCategoryRestoreDescription() { return getMessage(CATEGORY_RESTORE_DESCRIPTION); }
    public static String getCategoryRestoreSuccess() { return getMessage(CATEGORY_RESTORE_SUCCESS); }
    public static String getCategoryRestoreBadRequest() { return getMessage(CATEGORY_RESTORE_BAD_REQUEST); }
    public static String getCategoryRestoreNotFound() { return getMessage(CATEGORY_RESTORE_NOT_FOUND); }
    public static String getCategoryRestoreParamId() { return getMessage(CATEGORY_RESTORE_PARAM_ID); }
    public static String getCategoryRestoreParamUserId() { return getMessage(CATEGORY_RESTORE_PARAM_USER_ID); }
    
    public static final String CATEGORY_CONTROLLER_TAG = "api.category.controller.tag";
    public static final String CATEGORY_CONTROLLER_TAG_DESCRIPTION = "api.category.controller.tag.description";
    
    public static String getCategoryControllerTag() { return getMessage(CATEGORY_CONTROLLER_TAG); }
    public static String getCategoryControllerTagDescription() { return getMessage(CATEGORY_CONTROLLER_TAG_DESCRIPTION); }
}

