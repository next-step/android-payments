package nextstep.payments.ui

object CardInfoUiFormatter {
    fun getFormattedCardNumber(cardNumber: String): String {
        return cardNumber.chunked(4)
            .mapIndexed { index, part ->
                if (index in 2..3) {
                    "****"
                } else {
                    part
                }
            }
            .joinToString("-")
    }

    fun getFormattedExpiredDate(expiredDate: String): String {
        return expiredDate.chunked(2).joinToString(" / ")
    }
}