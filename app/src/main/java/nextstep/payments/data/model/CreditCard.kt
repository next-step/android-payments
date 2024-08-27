package nextstep.payments.data.model

data class CreditCard(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String
) {
    val firstCardDigits: String =
        if (cardNumber.length >= 4) cardNumber.substring(0, 4)
        else cardNumber.substring(0, cardNumber.length)

    val secondCardDigits: String =
        if (cardNumber.length >= 8) cardNumber.substring(4, 8)
        else cardNumber.substring(4, cardNumber.length)
}