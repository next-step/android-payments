package nextstep.payments.model

data class Card(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String
)
