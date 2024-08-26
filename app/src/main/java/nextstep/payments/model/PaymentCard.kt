package nextstep.payments.model

data class PaymentCard(
    val cardNumber: String,
    val expiryDate: String,
    val password: String,
    val ownerName: String? = null,
) {
    companion object {
        val default = PaymentCard(
            cardNumber = "",
            expiryDate = "",
            password = "",
            ownerName = null
        )
    }
}