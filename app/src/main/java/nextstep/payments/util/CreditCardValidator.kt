package nextstep.payments.util

object CreditCardValidator {

    fun validateName(name: String): Boolean {
        return name.length <= NAME_LENGTH_LIMIT
    }

    fun validatePassword(password: String): Boolean {
        return password.matches(PASSWORD_REGEX.toRegex())
    }

    fun validateNumber(number: String): Boolean {
        return number.matches(NUMBER_REGEX.toRegex())
    }

    fun validateDueDate(dueDate: String): Boolean {
        return dueDate.matches(DUE_DATE_REGEX.toRegex())
    }

    private const val NAME_LENGTH_LIMIT = 30
    private const val PASSWORD_REGEX = "^\\d{4}$"
    private const val NUMBER_REGEX = "^\\d{16}$"
    private const val DUE_DATE_REGEX = "^\\d{4}$"
}