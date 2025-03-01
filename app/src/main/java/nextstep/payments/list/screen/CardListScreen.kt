package nextstep.payments.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.list.CardListViewModel
import nextstep.payments.R
import nextstep.payments.common.model.Card
import nextstep.payments.list.component.CardListTopBar
import nextstep.payments.list.model.CardUiState

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    moveToAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    CardListScreen(
        uiState = uiState.value,
        moveToAddCard = moveToAddCard,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    uiState: CardUiState,
    moveToAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardListTopBar(
                rightButton = {
                    if (uiState is CardUiState.Many) {
                        Text(
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .clickable { moveToAddCard() },
                            text = stringResource(R.string.add),
                            color = Color.Black,
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                            lineHeight = 36.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (uiState) {
            CardUiState.Empty -> EmptyCardsScreen(
                modifier = modifier.padding(innerPadding),
                moveToAddCard = moveToAddCard,
            )

            is CardUiState.One -> OneCardScreen(
                modifier = modifier.padding(innerPadding),
                card = uiState.card,
                moveToAddCard = moveToAddCard,
            )

            is CardUiState.Many -> ManyCardsScreen(
                modifier = modifier.padding(innerPadding),
                cards = uiState.cards
            )
        }
    }
}


private class UiStatePreviewParameterProvider : PreviewParameterProvider<CardUiState> {
    override val values = sequenceOf(
        CardUiState.Empty,
        CardUiState.One(
            Card(
                cardNumber = "1111 - 2222 - **** - ****",
                expiredDate = "12/25",
                ownerName = "CREW",
                password = "1234",
            )
        ),
        CardUiState.Many(
            List(5) {
                Card(
                    cardNumber = "1111 - 2222 - **** - ****",
                    expiredDate = "12/25",
                    ownerName = "CREW",
                    password = "1234",
                )
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CardEmptyScreenPreview(
    @PreviewParameter(UiStatePreviewParameterProvider::class) uiState: CardUiState
) {
    CardListScreen(
        uiState = uiState,
        moveToAddCard = {},
    )
}
