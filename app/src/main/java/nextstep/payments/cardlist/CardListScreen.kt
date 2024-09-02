package nextstep.payments.cardlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.model.CardNumber
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.YearMonth

@Composable
internal fun CardListScreen(
    uiState: CardListUiState,
    onAddCardClick: () -> Unit,
) {
    when (uiState) {
        is CardListUiState.Empty ->
            CardListEmptyScreen(
                onAddCardClick = onAddCardClick,
            )

        is CardListUiState.One ->
            CardListOneScreen(
                card = uiState.card,
                onAddCardClick = onAddCardClick,
            )

        is CardListUiState.Many -> CardListManyScreen(
            cards = uiState.cards,
            onAddCardClick = onAddCardClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CardListTopAppBar(
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.card_list_top_app_bar_title))
        },
        actions = actions
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardListScreenEmptyPreview() {
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.Empty,
            onAddCardClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardListScreenOnePreview() {
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.One(
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
            onAddCardClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardListScreenManyPreview() {
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.Many(
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
            ),
            onAddCardClick = {}
        )
    }
}