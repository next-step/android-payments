package nextstep.payments.model

object CardFormFormatter {
    private const val MAX_CARD_NUMBER_LENGTH = 16
    private const val MAX_EXPIRED_DATE_LENGTH = 4
    private const val MAX_OWNER_NAME_LENGTH = 10
    private const val MAX_PASSWORD_LENGTH = 4

    fun formatCardNumber(cardNumber: String): String =
        formatDigit(cardNumber, MAX_CARD_NUMBER_LENGTH)

    fun formatExpiredDate(expiredDate: String): String =
        formatDigit(expiredDate, MAX_EXPIRED_DATE_LENGTH)

    fun formatOwnerName(ownerName: String): String =
        formatLetter(ownerName, MAX_OWNER_NAME_LENGTH)

    fun formatPassword(password: String): String =
        formatDigit(password, MAX_PASSWORD_LENGTH)

    private fun formatDigit(value: String, maxLength: Int): String {
        val filteredValue = value.filter { it.isDigit() }
        if (filteredValue.length > maxLength) return filteredValue.take(maxLength)
        return filteredValue
    }

    private fun formatLetter(value: String, maxLength: Int): String {
        val filteredValue = value.filter { it.isLetter() }
        if (filteredValue.length > maxLength) return filteredValue.take(maxLength)
        return filteredValue
    }
}
