package nextstep.payments.model

interface Card {
    val cardNumber: String
    val expiredDate: String
    val ownerName: String
    val password: String

    fun getFormattedCardNumber(): String
    fun getFormattedExpiredDate(): String
}