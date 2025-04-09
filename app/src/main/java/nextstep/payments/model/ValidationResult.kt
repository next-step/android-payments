package nextstep.payments.model

enum class ValidationResult() {
    SUCCESS,
    EMPTY,
    ADDITIONAL_INPUT_REQUIRED,
    INPUT_REJECTED
}