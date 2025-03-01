package nextstep.payments.data.model

data class Card(
    val id: String,
    val number: String,
    val ownerName: String,
    val password: String,
    val expiredDate: String,
    val company: CardCompany?,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
) {
    companion object {
        val Empty = Card(
            id = "empty",
            number = "",
            ownerName = "",
            password = "",
            expiredDate = "",
            company = null,
        )
    }
}