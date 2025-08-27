package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_ERROR("unexpected.error"),
    GUIDE_NOT_FOUND("not.found-guide"),
    TOUR_NOT_FOUND("not.found-tour"),
    TOUR_DATE_CONFLICT("conflict-tour.date");

    private final String code;
}