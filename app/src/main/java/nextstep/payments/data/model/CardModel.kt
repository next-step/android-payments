package nextstep.payments.data.model

data class CardModel(
    val number: String,
    val ownerName: String,
    val password: String,
    val expiredDate: String,
) {
    constructor() : this(
        number = "",
        ownerName = "",
        password = "",
        expiredDate = "",
    )
}