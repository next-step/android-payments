package nextstep.payments.model

data class Card(
    val id : Int,
    val cardNumber : String,
    val expiredDate : String,
    val ownerName : String,
    val password : String,
    val bankType : BankType = BankType.NOT_SELECTED,
)
