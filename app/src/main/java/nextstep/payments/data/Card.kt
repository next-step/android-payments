package nextstep.payments.data

data class Card(
    val id: Int,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bankType: BankType?,
) {
    fun formatCardNumber(): String {
        val first = cardNumber.take(4)
        val second = cardNumber.drop(4).take(4)
        val third = cardNumber.drop(8).take(4)
        val fourth = cardNumber.drop(12).take(4)

        val sb = StringBuilder()

        sb.append(first)

        if (first.length == 4 && second.isNotEmpty()) {
            sb.append(" - ")
        }

        sb.append(second)

        if (second.length == 4 && third.isNotEmpty()) {
            sb.append(" - ")
        }

        sb.append(third.map { "*" }.joinToString(""))

        if (third.length == 4 && fourth.isNotEmpty()) {
            sb.append(" - ")
        }

        sb.append(fourth.map { "*" }.joinToString(""))

        return sb.toString()
    }

    fun formatExpiredDate(): String {
        val first = expiredDate.take(2)
        val second = expiredDate.drop(2)

        val sb = StringBuilder()

        sb.append(first)

        if (first.length == 2 && second.isNotEmpty()) {
            sb.append(" / ")
        }

        sb.append(second)

        return sb.toString()
    }
}

val dummyDataList = arrayListOf(
    Card(
        id = 0,
        cardNumber = "1234567890123456",
        expiredDate = "1201",
        ownerName = "홍길동",
        password = "0000",
        bankType = BankType.KB
    ),
    Card(
        id = 1,
        cardNumber = "5678901234567890",
        expiredDate = "0101",
        ownerName = "김철수",
        password = "0000",
        bankType = BankType.SHINHAN
    ),
    Card(
        id = 2,
        cardNumber = "9012345678901234",
        expiredDate = "0201",
        ownerName = "김민수",
        password = "0000",
        bankType = BankType.WOORI
    ),
    Card(
        id = 3,
        cardNumber = "1111222233334444",
        expiredDate = "1212",
        ownerName = "김영식",
        password = "0000",
        bankType = BankType.HANA
    )
)