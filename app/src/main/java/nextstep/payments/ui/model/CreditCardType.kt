package nextstep.payments.ui.model

sealed class CreditCardType {
    data object NoCardType : CreditCardType()
    data class AddingCard(val cardName: String) : CreditCardType()
    data class CardInfo(
        val number: String,
        val expiredDate: String,
        val ownerName: String,
        val password: String,
        val cardName: String
    ) : CreditCardType()
}
