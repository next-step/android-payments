package nextstep.payments.ui.cardlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.model.CardNumber
import nextstep.payments.model.CreditCard
import java.time.YearMonth

internal class CardListUiStateProvider : PreviewParameterProvider<CardListUiState> {
    override val values = sequenceOf(
        CardListUiState.Empty,
        CardListUiState.One(
            CreditCard(
                cardNumbers = listOf(
                    CardNumber("1111"),
                    CardNumber("1111"),
                    CardNumber("1111"),
                    CardNumber("1111"),
                ),
                expiredDate = YearMonth.now(),
                ownerName = "이범석",
                password = "1234"
            )
        ),
        CardListUiState.Many(
            listOf(
                CreditCard(
                    cardNumbers = listOf(
                        CardNumber("1111"),
                        CardNumber("1111"),
                        CardNumber("1111"),
                        CardNumber("1111"),
                    ),
                    expiredDate = YearMonth.now(),
                    ownerName = "이범석",
                    password = "1234"
                ),
                CreditCard(
                    cardNumbers = listOf(
                        CardNumber("1111"),
                        CardNumber("1111"),
                        CardNumber("1111"),
                        CardNumber("1112"),
                    ),
                    expiredDate = YearMonth.now(),
                    ownerName = "이범석",
                    password = "1234"
                )
            )
        )
    )
}
