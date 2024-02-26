package nextstep.payments.domain

data class PaymentCard(
    val id: String,
    val cardNumber: String,
    val ownerName: String,
    val expirationDate: String,
    val cvcNumber: String,
    val password: String
) {
    companion object {
        val MockData = PaymentCard(
            id = "1",
            cardNumber = "1111222233334444",
            ownerName = "Namjackson",
            expirationDate = "0425",
            cvcNumber = "123",
            password = "1234"
        )
    }
}
