package nextstep.payments.data.model

import java.io.Serializable

data class Card(
    val id: Int = 0,
    val bank: Bank,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
): Serializable

val dummyData = Card(
    id = 0,
    bank = Bank.BC,
    cardNumber = "1111 - 1111 - 1111 - 1111",
    expiredDate = "01 / 22",
    ownerName = "User",
    password = "1234"
)
