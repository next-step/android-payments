package nextstep.payments.ui.component.card

import nextstep.payments.model.card.CardNumber
import java.time.YearMonth
import java.util.UUID

data class CardInformation(
    val id: UUID,
    val numberFirst: CardNumber,
    val numberSecond: CardNumber,
    val ownerName: String,
    val expirationDate: YearMonth,
    val bank: CardBankInformation,
)