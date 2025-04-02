package nextstep.payments.model

data class CreditCard(
    override val cardNumber: String,
    override val expiredDate: String,
    override val ownerName: String,
    override val password: String
) : Card