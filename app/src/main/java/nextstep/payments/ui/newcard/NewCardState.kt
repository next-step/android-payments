package nextstep.payments.ui.newcard

import nextstep.payments.constants.DateConstants.YEAR_DATE_FORMAT
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import java.time.YearMonth
import java.time.format.DateTimeFormatter

data class NewCardState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val selectedBank: BankType = BankType.NOT_SELECTED,
    val cardAdded: Boolean = false
) {
    fun toCard(): Card {
        val toYearMonth = YearMonth.parse(
            expiredDate,
            DateTimeFormatter.ofPattern(YEAR_DATE_FORMAT)
        )
        return Card(
            type = selectedBank,
            number = cardNumber,
            expiredDate = toYearMonth,
            ownerName = ownerName,
            password = password
        )
    }
}
