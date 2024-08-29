package nextstep.payments.data.model

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bank: Bank
)

val dummyData = Card(
    cardNumber = "1111 - 1111 - 1111 - 1111",
    expiredDate = "01 / 22",
    ownerName = "User",
    password = "1234",
    bank = Bank.KB
)
