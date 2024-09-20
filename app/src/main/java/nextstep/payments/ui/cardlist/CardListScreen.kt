package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import nextstep.payments.R
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CardListScreen(
    uiState: CardListUiState,
    onAddCardClick: () -> Unit,
    onCardClick: (CardInformation) -> Unit,
    modifier: Modifier = Modifier,
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
                onCardClick = { onCardClick(uiState.card) },
                modifier = modifier,
            )

        is CardListUiState.Many -> CardListManyScreen(
            cards = uiState.cards,
            onAddCardClick = onAddCardClick,
            onCardClick = onCardClick
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

@PreviewLightDark
@Composable
private fun CardListScreenPreview(
    @PreviewParameter(CardListUiStateProvider::class) uiState: CardListUiState
) {
    PaymentsTheme {
        CardListScreen(
            uiState = uiState,
            onAddCardClick = {},
            onCardClick = {},
        )
    }
}