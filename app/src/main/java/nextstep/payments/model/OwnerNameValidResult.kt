package nextstep.payments.model

enum class OwnerNameValidResult {
    NONE,
    VALID,
    ERROR_OWNER_NAME_LENGTH,
    ;

    fun isError() = this == ERROR_OWNER_NAME_LENGTH

    fun isValid() = this == VALID
}
