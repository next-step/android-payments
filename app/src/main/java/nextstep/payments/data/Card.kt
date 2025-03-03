package nextstep.payments.data

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)


val dummyDataList = arrayListOf(
    Card(
        cardNumber = "0134 - 5678 - **** - ****",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000"
    ),
    Card(
        cardNumber = "1223 - 5678 - **** - ****",
        expiredDate = "00 / 00",
        ownerName = "김철수",
        password = "0000"
    ),
    Card(
        cardNumber = "1234 - 5678 - **** - ****",
        expiredDate = "00 / 00",
        ownerName = "김민수",
        password = "0000"
    ),
    Card(
        cardNumber = "1234 - 1241 - **** - ****",
        expiredDate = "00 / 00",
        ownerName = "김영식",
        password = "0000"
    )
)