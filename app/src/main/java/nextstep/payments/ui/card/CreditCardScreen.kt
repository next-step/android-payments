package nextstep.payments.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.theme.TopBarTitleColor


@Composable
fun CreditCardScreen(
    navigateToNewCard: () -> Unit,
    navigateToEditCard: (Card) -> Unit,
    viewModel: CreditCardViewModel = viewModel()
) {
    val creditCardUiState by viewModel.creditCardUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardListTopAppBar(
                uiState = creditCardUiState,
                onClickAddCard = navigateToNewCard
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreditCardScreen(
                uiState = creditCardUiState,
                onClickAddItem = navigateToNewCard,
                onClickCard = navigateToEditCard
            )
        }
    }
}

@Composable
fun CreditCardScreen(
    uiState: CreditCardUiState,
    onClickAddItem: () -> Unit,
    onClickCard: (Card) -> Unit
) {
    when (uiState) {
        is CreditCardUiState.Empty ->
            EmptyCardScreen(
                onClickItem = onClickAddItem
            )
        is CreditCardUiState.One ->
            SingleCardScreen(
                card = uiState.card,
                onClickAddItem = onClickAddItem,
                onClickCard = onClickCard
            )
        is CreditCardUiState.Many ->
            CardListScreen(
                cards = uiState.cards,
                onClickCard = onClickCard
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppBar(
    uiState: CreditCardUiState,
    onClickAddCard: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.payments),
                color = TopBarTitleColor,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            if (uiState is CreditCardUiState.Many) {
                TextButton(
                    onClick = { onClickAddCard() }
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CreditCardScreenPreview(
    @PreviewParameter(CardListScreenPreviewParameterProvider::class) value: CreditCardUiState
) {
    CreditCardScreen(
        uiState = value,
        onClickAddItem = {},
        onClickCard = {}
    )
}

private class CardListScreenPreviewParameterProvider : PreviewParameterProvider<CreditCardUiState> {
    val card1 = Card(
        cardNumber = "0000 - 1111 - **** - ****",
        cardExpiredDate = "08/27",
        cardOwnerName = "Park",
        cardPassword = "1234",
        bankType = BankType.SHINHAN
    )
    val card2 = Card(
        cardNumber = "0000 - 2222 - **** - ****",
        cardExpiredDate = "08/27",
        cardOwnerName = "Park",
        cardPassword = "1234",
        bankType = BankType.LOTTE
    )
    override val values: Sequence<CreditCardUiState> = sequenceOf(
        CreditCardUiState.Empty,
        CreditCardUiState.One(card1),
        CreditCardUiState.Many(listOf(card1, card2))
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopAppBarPreview() {
    val emptyUiState = CreditCardUiState.Empty
    val manyUiState = CreditCardUiState.Many(
        listOf(
            Card(
                cardNumber = "1111 - 2222 - **** - ****",
                cardOwnerName = "Park",
                cardExpiredDate = "04 / 21",
                cardPassword = "1234",
                bankType = BankType.SHINHAN
            ),
            Card(
                cardNumber = "1111 - 3333 - **** - ****",
                cardOwnerName = "Park",
                cardExpiredDate = "04 / 21",
                cardPassword = "1234",
                bankType = BankType.BC
            )
        )
    )

    Column {
        CardListTopAppBar(emptyUiState, {})
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color.Black)
        )
        CardListTopAppBar(manyUiState, {})
    }
}
