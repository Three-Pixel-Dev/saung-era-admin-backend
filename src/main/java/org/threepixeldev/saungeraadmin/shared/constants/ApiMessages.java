package org.threepixeldev.saungeraadmin.shared.constants;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Constants class for API messages loaded from messages.properties
 * This class provides a centralized way to access all API messages
 */
@Component
public class ApiMessages {
    
    private final MessageSource messageSource;
    
    public ApiMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    // Validation Messages
    public String getValidationCategoryNameRequired() {
        return messageSource.getMessage("validation.category.name.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductNameRequired() {
        return messageSource.getMessage("validation.product.name.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductQuantityRequired() {
        return messageSource.getMessage("validation.product.quantity.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductQuantityPositive() {
        return messageSource.getMessage("validation.product.quantity.positive", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductPriceRequired() {
        return messageSource.getMessage("validation.product.price.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductPricePositive() {
        return messageSource.getMessage("validation.product.price.positive", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductDiscountPositive() {
        return messageSource.getMessage("validation.product.discount.positive", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationProductWeightPositive() {
        return messageSource.getMessage("validation.product.weight.positive", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationUserNameRequired() {
        return messageSource.getMessage("validation.user.name.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationUserUsernameRequired() {
        return messageSource.getMessage("validation.user.username.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationUserEmailRequired() {
        return messageSource.getMessage("validation.user.email.required", null, LocaleContextHolder.getLocale());
    }
    
    public String getValidationUserEmailInvalid() {
        return messageSource.getMessage("validation.user.email.invalid", null, LocaleContextHolder.getLocale());
    }
    
    // Category API Messages
    public String getCategoryDescription() {
        return messageSource.getMessage("api.category.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryResponseDescription() {
        return messageSource.getMessage("api.category.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryIdDescription() {
        return messageSource.getMessage("api.category.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryNameDescription() {
        return messageSource.getMessage("api.category.name.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDescriptionDescription() {
        return messageSource.getMessage("api.category.description.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryControllerTag() {
        return messageSource.getMessage("api.category.controller.tag", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryControllerTagDescription() {
        return messageSource.getMessage("api.category.controller.tag.description", null, LocaleContextHolder.getLocale());
    }
    
    // Category Operations
    public String getCategoryGetAllSummary() {
        return messageSource.getMessage("api.category.get.all.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetAllDescription() {
        return messageSource.getMessage("api.category.get.all.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetAllSuccess() {
        return messageSource.getMessage("api.category.get.all.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetByIdSummary() {
        return messageSource.getMessage("api.category.get.by.id.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetByIdDescription() {
        return messageSource.getMessage("api.category.get.by.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetByIdSuccess() {
        return messageSource.getMessage("api.category.get.by.id.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetByIdNotFound() {
        return messageSource.getMessage("api.category.get.by.id.not.found", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryGetByIdParamId() {
        return messageSource.getMessage("api.category.get.by.id.param.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateSummary() {
        return messageSource.getMessage("api.category.create.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateDescription() {
        return messageSource.getMessage("api.category.create.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateSuccess() {
        return messageSource.getMessage("api.category.create.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateBadRequest() {
        return messageSource.getMessage("api.category.create.bad.request", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateParamRequest() {
        return messageSource.getMessage("api.category.create.param.request", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryCreateParamUserId() {
        return messageSource.getMessage("api.category.create.param.user.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateSummary() {
        return messageSource.getMessage("api.category.update.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateDescription() {
        return messageSource.getMessage("api.category.update.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateSuccess() {
        return messageSource.getMessage("api.category.update.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateBadRequest() {
        return messageSource.getMessage("api.category.update.bad.request", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateNotFound() {
        return messageSource.getMessage("api.category.update.not.found", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateParamId() {
        return messageSource.getMessage("api.category.update.param.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateParamRequest() {
        return messageSource.getMessage("api.category.update.param.request", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryUpdateParamUserId() {
        return messageSource.getMessage("api.category.update.param.user.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteSummary() {
        return messageSource.getMessage("api.category.delete.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteDescription() {
        return messageSource.getMessage("api.category.delete.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteSuccess() {
        return messageSource.getMessage("api.category.delete.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteNotFound() {
        return messageSource.getMessage("api.category.delete.not.found", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteParamId() {
        return messageSource.getMessage("api.category.delete.param.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryDeleteParamUserId() {
        return messageSource.getMessage("api.category.delete.param.user.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryHardDeleteSummary() {
        return messageSource.getMessage("api.category.hard.delete.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryHardDeleteDescription() {
        return messageSource.getMessage("api.category.hard.delete.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryHardDeleteSuccess() {
        return messageSource.getMessage("api.category.hard.delete.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryHardDeleteNotFound() {
        return messageSource.getMessage("api.category.hard.delete.not.found", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryHardDeleteParamId() {
        return messageSource.getMessage("api.category.hard.delete.param.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreSummary() {
        return messageSource.getMessage("api.category.restore.summary", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreDescription() {
        return messageSource.getMessage("api.category.restore.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreSuccess() {
        return messageSource.getMessage("api.category.restore.success", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreBadRequest() {
        return messageSource.getMessage("api.category.restore.bad.request", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreNotFound() {
        return messageSource.getMessage("api.category.restore.not.found", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreParamId() {
        return messageSource.getMessage("api.category.restore.param.id", null, LocaleContextHolder.getLocale());
    }
    
    public String getCategoryRestoreParamUserId() {
        return messageSource.getMessage("api.category.restore.param.user.id", null, LocaleContextHolder.getLocale());
    }
    
    // Product API Messages
    public String getProductDescription() {
        return messageSource.getMessage("api.product.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductResponseDescription() {
        return messageSource.getMessage("api.product.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductIdDescription() {
        return messageSource.getMessage("api.product.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductNameDescription() {
        return messageSource.getMessage("api.product.name.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductDescriptionDescription() {
        return messageSource.getMessage("api.product.description.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductQuantityDescription() {
        return messageSource.getMessage("api.product.quantity.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductPriceDescription() {
        return messageSource.getMessage("api.product.price.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductDiscountTypeDescription() {
        return messageSource.getMessage("api.product.discount.type.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductDiscountAmountDescription() {
        return messageSource.getMessage("api.product.discount.amount.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductShortDescriptionDescription() {
        return messageSource.getMessage("api.product.short.description.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductLongDescriptionDescription() {
        return messageSource.getMessage("api.product.long.description.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductWeightDescription() {
        return messageSource.getMessage("api.product.weight.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductCountryIdDescription() {
        return messageSource.getMessage("api.product.country.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductCategoriesDescription() {
        return messageSource.getMessage("api.product.categories.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getProductCategoryIdsDescription() {
        return messageSource.getMessage("api.product.category.ids.description", null, LocaleContextHolder.getLocale());
    }
    
    // User API Messages
    public String getUserDescription() {
        return messageSource.getMessage("api.user.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserResponseDescription() {
        return messageSource.getMessage("api.user.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserIdDescription() {
        return messageSource.getMessage("api.user.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserNameDescription() {
        return messageSource.getMessage("api.user.name.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserUsernameDescription() {
        return messageSource.getMessage("api.user.username.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserEmailDescription() {
        return messageSource.getMessage("api.user.email.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserPhoneNumberDescription() {
        return messageSource.getMessage("api.user.phone.number.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getUserPasswordDescription() {
        return messageSource.getMessage("api.user.password.description", null, LocaleContextHolder.getLocale());
    }
    
    // Order API Messages
    public String getOrderResponseDescription() {
        return messageSource.getMessage("api.order.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderIdDescription() {
        return messageSource.getMessage("api.order.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderUserIdDescription() {
        return messageSource.getMessage("api.order.user.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderUserNameDescription() {
        return messageSource.getMessage("api.order.user.name.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderUserEmailDescription() {
        return messageSource.getMessage("api.order.user.email.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderTotalPriceDescription() {
        return messageSource.getMessage("api.order.total.price.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderOrderedDateDescription() {
        return messageSource.getMessage("api.order.ordered.date.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderPromotionCodeDescription() {
        return messageSource.getMessage("api.order.promotion.code.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderPromotionIdDescription() {
        return messageSource.getMessage("api.order.promotion.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderItemsDescription() {
        return messageSource.getMessage("api.order.items.description", null, LocaleContextHolder.getLocale());
    }
    
    // Order Item API Messages
    public String getOrderItemResponseDescription() {
        return messageSource.getMessage("api.order.item.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderItemIdDescription() {
        return messageSource.getMessage("api.order.item.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderItemQuantityDescription() {
        return messageSource.getMessage("api.order.item.quantity.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getOrderItemPriceDescription() {
        return messageSource.getMessage("api.order.item.price.description", null, LocaleContextHolder.getLocale());
    }
    
    // Master Data API Messages
    public String getMasterDataDescription() {
        return messageSource.getMessage("api.master.data.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataCreatedAtDescription() {
        return messageSource.getMessage("api.master.data.created.at.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataUpdatedAtDescription() {
        return messageSource.getMessage("api.master.data.updated.at.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataDeletedAtDescription() {
        return messageSource.getMessage("api.master.data.deleted.at.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataCreatedByDescription() {
        return messageSource.getMessage("api.master.data.created.by.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataUpdatedByDescription() {
        return messageSource.getMessage("api.master.data.updated.by.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getMasterDataDeletedByDescription() {
        return messageSource.getMessage("api.master.data.deleted.by.description", null, LocaleContextHolder.getLocale());
    }
    
    // Shared User Response API Messages
    public String getSharedUserResponseDescription() {
        return messageSource.getMessage("api.shared.user.response.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getSharedUserResponseIdDescription() {
        return messageSource.getMessage("api.shared.user.response.id.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getSharedUserResponseNameDescription() {
        return messageSource.getMessage("api.shared.user.response.name.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getSharedUserResponseUsernameDescription() {
        return messageSource.getMessage("api.shared.user.response.username.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getSharedUserResponseEmailDescription() {
        return messageSource.getMessage("api.shared.user.response.email.description", null, LocaleContextHolder.getLocale());
    }
    
    public String getSharedUserResponsePhoneNumberDescription() {
        return messageSource.getMessage("api.shared.user.response.phone.number.description", null, LocaleContextHolder.getLocale());
    }
}

