package nextstep.payments.ui.newcard

object NewCardInputValidator {

    private val ONLY_NUMBER_REGEX = "^[0-9]+$".toRegex()
    private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()

    fun validateCardNumber(cardNumber: String): Boolean {
        if (cardNumber.length != 16)
            return false
        else if (!cardNumber.matches(ONLY_NUMBER_REGEX))
            return false
        return true
    }

    fun validateExpiredDate(expiredDate: String): Boolean {
        if (expiredDate.length != 4)
            return false
        else if (!expiredDate.matches(ONLY_NUMBER_REGEX))
            return false
        return true
    }

    fun validateOwnerName(ownerName: String): Boolean {
        if (ownerName.length > 30)
            return false
        return true
    }

    fun validatePassword(password: String): Boolean {
        return when {
            password.isBlank() ||
            password.length !in 8..16 ||
            !password.matches(PASSWORD_REGEX) -> false
            else -> true
        }
    }
}