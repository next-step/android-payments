package nextstep.payments.data

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) {
    fun formatCardNumber(): String {
        return cardNumber.take(4) + " - " + cardNumber.drop(4).take(4) + " - **** - ****"
    }

    fun formatExpiredDate(): String {
        return expiredDate.take(2) + " / " + expiredDate.drop(2)
    }
}

val dummyDataList = arrayListOf(
    Card(
        cardNumber = "1234567890123456",
        expiredDate = "1201",
        ownerName = "홍길동",
        password = "0000"
    ),
    Card(
        cardNumber = "5678901234567890",
        expiredDate = "0101",
        ownerName = "김철수",
        password = "0000"
    ),
    Card(
        cardNumber = "9012345678901234",
        expiredDate = "0201",
        ownerName = "김민수",
        password = "0000"
    ),
    Card(
        cardNumber = "1111222233334444",
        expiredDate = "1212",
        ownerName = "김영식",
        password = "0000"
    )
)