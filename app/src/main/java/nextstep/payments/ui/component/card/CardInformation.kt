package nextstep.payments.ui.component.card

import nextstep.payments.model.card.CardNumber
import java.time.YearMonth

data class CardInformation(
    val numberFirst: CardNumber,
    val numberSecond: CardNumber,
    val ownerName: String,
    val expirationDate: YearMonth,
    val bank: CardBankInformation,
)