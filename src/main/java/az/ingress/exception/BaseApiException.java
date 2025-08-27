package az.ingress.exception;

import static az.ingress.model.constants.LocalizationConstants.ERROR_BUNDLE;
import static az.ingress.util.LocalizationUtil.LOCALIZATION_UTIL;

public abstract class BaseApiException extends RuntimeException {

    public BaseApiException(ErrorMessage message, Object... arguments) {
        super(LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, message.getCode()).formatted(arguments));
    }
}