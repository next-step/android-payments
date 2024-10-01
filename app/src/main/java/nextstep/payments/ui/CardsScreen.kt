package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.model.Card
import nextstep.payments.model.CardUiState
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardsViewModel


@Composable
fun CardsScreen(onCardAddClicked: () -> Unit, viewModel: CardsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val cards by viewModel.cards.collectAsState()

    CardsScreenContent(
        onCardAddClicked = onCardAddClicked,
        cards = cards,
        uiState = uiState
    )
}

@Composable
fun CardsScreenContent(onCardAddClicked: () -> Unit, cards: List<Card>, uiState: CardUiState) {
    Scaffold(
        topBar = {
            when (uiState) {
                is CardUiState.Empty -> {
                    CardsTopBar()
                }

                is CardUiState.One -> {
                    CardsTopBar()
                }

                is CardUiState.Many -> {
                    CardsTopBarWithButton(onCardAddClicked)
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardList(uiState, cards, onCardAddClicked)
        }
    }
}

@Composable
fun CardList(cardUiState: CardUiState, cards: List<Card>, onCardAddClicked: () -> Unit) {
    when (cardUiState) {
        CardUiState.Empty -> EmptyCardState(onCardAddClicked)
        is CardUiState.One -> OneCardState(cards, onCardAddClicked)
        is CardUiState.Many -> ManyCardState(cards)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardsTopBar(content: @Composable () -> Unit = {}) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.card_screen_top_bar_card_title),
                fontSize = 22.sp
            )
        },
        actions = {
            content()
        }
    )
}

@Composable
private fun CardsTopBarWithButton(onCardAddClicked: () -> Unit) {
    CardsTopBar(
        content = {
            Button(
                onClick = {
                    onCardAddClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Black
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.card_screen_top_bar_card_add),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardNotExistListPreview() {
    PaymentsTheme {
        CardsScreenContent(
            onCardAddClicked = {},
            cards = emptyList(),
            uiState = CardUiState.Empty
        )
    }
}

@Preview
@Composable
private fun CardOneExistListPreview() {
    PaymentsTheme {
        CardsScreenContent(
            onCardAddClicked = {},
            cards = listOf(
                Card(
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                    color = 0xFFF04651,
                    cardCompany = "BC카드"
                )
            ),
            uiState = CardUiState.One
        )
    }
}

@Preview
@Composable
private fun CardManyExistListPreview() {
    PaymentsTheme {
        CardsScreenContent(
            onCardAddClicked = {},
            cards = listOf(
                Card(
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/27",
                    ownerName = "제임스",
                    password = "1111",
                    color = 0xFFF04651,
                    cardCompany = "BC카드"
                ),
                Card(
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "02/26",
                    ownerName = "홍길동",
                    password = "0000",
                    color = 0xFF0E19ED,
                    cardCompany = "신한카드"
                )
            ),
            uiState = CardUiState.Many
        )
    }
}
