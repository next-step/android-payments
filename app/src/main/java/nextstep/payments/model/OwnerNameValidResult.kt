package nextstep.payments.model

enum class OwnerNameValidResult {
    NONE,
    VALID,
    ERROR_OWNER_NAME_LENGTH,
    ;

    fun isError() = this == ERROR_OWNER_NAME_LENGTH
}

enum class ExpiredDateMonthValidResult {
    NONE,
    VALID,
    ERROR_EXPIRED_DATE_MONTH_RANGE,
    ;

    fun isError() = this == ERROR_EXPIRED_DATE_MONTH_RANGE
}
