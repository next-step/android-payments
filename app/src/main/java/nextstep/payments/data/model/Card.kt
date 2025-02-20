package nextstep.payments.data.model

data class Card(
    val number: String,
    val ownerName: String,
    val password: String,
    val expiredDate: String,
)