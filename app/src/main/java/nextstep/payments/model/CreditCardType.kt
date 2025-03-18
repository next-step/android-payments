package nextstep.payments.model

sealed class CreditCardType {
    data object NoCardType : CreditCardType()
    data class CardInfo(
        val number: String,
        val expiredDate: String,
        val ownerName: String,
        val password: String
    ) : CreditCardType()
}
