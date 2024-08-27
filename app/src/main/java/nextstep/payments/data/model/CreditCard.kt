package nextstep.payments.data.model

data class CreditCard(
    val cardNumber : String,
    val expiredDate : String,
    val ownerName : String,
    val password : String
){
    private val cardNumberList : List<String> by lazy {
        cardNumber.split(" - ")
    }

    val firstCardDigits = cardNumberList.firstOrNull() ?: ""
    val secondCardDigits = cardNumberList.getOrNull(2) ?: ""
}