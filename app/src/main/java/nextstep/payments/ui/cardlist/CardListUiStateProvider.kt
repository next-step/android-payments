package nextstep.payments.ui.cardlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.model.card.CardNumber
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation
import java.time.YearMonth
import java.util.UUID

internal class CardListUiStateProvider : PreviewParameterProvider<CardListUiState> {
    override val values = sequenceOf(
        CardListUiState.Empty,
        CardListUiState.One(
            card = CardInformation(
                id = UUID.randomUUID(),
                numberFirst = CardNumber("1111"),
                numberSecond = CardNumber("1111"),
                expirationDate = YearMonth.now(),
                ownerName = "이범석",
                bank = CardBankInformation.Bc,
            )
        ),
        CardListUiState.Many(
            listOf(
                CardInformation(
                    id = UUID.randomUUID(),
                    numberFirst = CardNumber("1111"),
                    numberSecond = CardNumber("1111"),
                    expirationDate = YearMonth.now(),
                    ownerName = "이범석",
                    bank = CardBankInformation.Bc,
                ),
                CardInformation(
                    id = UUID.randomUUID(),
                    numberFirst = CardNumber("2222"),
                    numberSecond = CardNumber("3333"),
                    expirationDate = YearMonth.now(),
                    ownerName = "이범석",
                    bank = CardBankInformation.Kb,
                )
            )
        )
    )
}
