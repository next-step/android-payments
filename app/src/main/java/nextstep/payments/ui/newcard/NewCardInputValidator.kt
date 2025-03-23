package nextstep.payments.ui.newcard

import nextstep.payments.model.ValidationResult

object NewCardInputValidator {

    private val ONLY_NUMBER_REGEX = "^[0-9]+$".toRegex()

    fun validateCardNumber(cardNumber: String): ValidationResult {
        return when {
            cardNumber.isEmpty() -> ValidationResult.EMPTY
            cardNumber.length > 16 -> ValidationResult.INPUT_REJECTED
            !cardNumber.matches(ONLY_NUMBER_REGEX) -> ValidationResult.INPUT_REJECTED
            cardNumber.length < 16 -> ValidationResult.ADDITIONAL_INPUT_REQUIRED
            else -> ValidationResult.SUCCESS
        }
    }

    fun validateExpiredDate(expiredDate: String): ValidationResult {
        return when {
            expiredDate.isEmpty() -> ValidationResult.EMPTY
            expiredDate.length > 4 -> ValidationResult.INPUT_REJECTED
            !expiredDate.matches(ONLY_NUMBER_REGEX) -> ValidationResult.INPUT_REJECTED
            expiredDate.length < 4 -> ValidationResult.ADDITIONAL_INPUT_REQUIRED
            else -> ValidationResult.SUCCESS
        }
    }

    fun validateOwnerName(ownerName: String): ValidationResult {
        return when {
            ownerName.isEmpty() -> ValidationResult.EMPTY
            ownerName.length > 30 -> ValidationResult.INPUT_REJECTED
            else -> ValidationResult.SUCCESS
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isEmpty() -> ValidationResult.EMPTY
            password.length > 4 -> ValidationResult.INPUT_REJECTED
            !password.matches(ONLY_NUMBER_REGEX) -> ValidationResult.INPUT_REJECTED
            password.length < 4 -> ValidationResult.ADDITIONAL_INPUT_REQUIRED
            else -> ValidationResult.SUCCESS
        }
    }
}