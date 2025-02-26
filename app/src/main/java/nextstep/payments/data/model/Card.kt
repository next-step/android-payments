package nextstep.payments.data.model

data class Card(
    val id: Int = 0,
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankType = BankType.NOT_SELECTED
)
