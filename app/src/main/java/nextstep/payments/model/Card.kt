package nextstep.payments.model

import nextstep.payments.constants.DateConstants.YEAR_DATE_FORMAT
import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class Card(
    val type : BankType,
    val number: String,
    val expiredDate: YearMonth,
    val ownerName: String,
    val password: String
) {
    fun getStringExpiredDate(): String =
        expiredDate.format(DateTimeFormatter.ofPattern(YEAR_DATE_FORMAT))
}
