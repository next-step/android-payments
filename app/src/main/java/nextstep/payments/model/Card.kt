package nextstep.payments.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import nextstep.payments.constants.DateConstants.YEAR_DATE_FORMAT
import nextstep.payments.util.YearMonthSerializer
import java.time.YearMonth
import java.time.format.DateTimeFormatter


/**
 * @see YearMonthSerializer expiredDate 직렬화
 */
@Serializable
data class Card(
    val type: BankType,
    val number: String,
    @Contextual val expiredDate: YearMonth,
    val ownerName: String,
    val password: String
) {
    fun getStringExpiredDate(): String =
        expiredDate.format(DateTimeFormatter.ofPattern(YEAR_DATE_FORMAT))
}
