package nextstep.payments.model

data class CreditCard(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) {
    val maskedNumber: String
        get() {
            val parts = number.split(" - ")
            if (parts.size != 4) {
                return number
            }

            val maskedPart = "****"
            return "${parts[0]} - ${parts[1]} - $maskedPart - $maskedPart"
        }

    companion object {
        val mock = CreditCard(
            number = "1234 - 5678 - 9012 - 3456",
            expiredDate = "12/26",
            ownerName = "홍길동",
            password = "1234",
        )
    }
}