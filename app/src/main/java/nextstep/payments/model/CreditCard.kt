package nextstep.payments.model

data class CreditCard(
    val cardNumber: String,
    val expiredDate: String,
    val password: String,
    val ownerName: String? = null,
) {
    companion object {
        val default = CreditCard(
            cardNumber = "",
            expiredDate = "",
            password = "",
            ownerName = null
        )
    }
}