package nextstep.payments.data.model

data class Card(
    val number: String,
    val ownerName: String,
    val password: String,
    val expiredDate: String,
    val company: CardCompany?,
    val created: Long = System.currentTimeMillis(),
) {
    companion object {
        val Empty = Card(
            number = "",
            ownerName = "",
            password = "",
            expiredDate = "",
            company = null,
        )
    }
}