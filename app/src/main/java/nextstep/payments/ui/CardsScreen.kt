package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.model.Card
import nextstep.payments.model.CardUiState
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardsViewModel

@Composable
fun CardsScreen(onCardAddClicked: () -> Unit, cards: List<Card>, viewModel: CardsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
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
            when (uiState) {
                is CardUiState.Empty -> {
                    Text(
                        text = stringResource(id = R.string.card_screen_card_add_information),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(32.dp)
                    )
                    CardList(
                        cards = cards,
                        content = { CardAdd(onCardAddClicked = onCardAddClicked) }
                    )
                }

                is CardUiState.One -> {
                    CardList(
                        cards = cards,
                        content = { CardAdd(onCardAddClicked = onCardAddClicked) }
                    )
                }

                is CardUiState.Many -> {
                    CardList(cards = cards)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(content: @Composable () -> Unit = {}) {
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
fun CardList(
    cards: List<Card>,
    content: @Composable () -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        itemsIndexed(cards) { _, card ->
            PaymentCard(
                content = {
                    Column(
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = card.cardNumber,
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Start,
                                style = TextStyle(letterSpacing = 3.sp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ) {
                            Text(
                                text = card.ownerName,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                            Text(
                                text = card.expiredDate,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            )
        }
        item {
            content()
        }
    }
}

@Preview
@Composable
fun CardNotExistListPreview() {
    PaymentsTheme {
        CardsScreen(
            onCardAddClicked = {},
            cards = emptyList(),
            viewModel = CardsViewModel()
        )
    }
}

@Preview
@Composable
fun CardOneExistListPreview() {
    PaymentsTheme {
        val cardViewModel = CardsViewModel()
        cardViewModel.notifyCardAdded()
        CardsScreen(
            onCardAddClicked = {},
            cards = listOf(Card("1234-5678-9012-3456", "12/34", "홍길동", "1234")),
            viewModel = cardViewModel
        )
    }
}

@Preview
@Composable
fun CardManyExistListPreview() {
    PaymentsTheme {
        val cardViewModel = CardsViewModel()
        cardViewModel.notifyCardAdded()
        cardViewModel.notifyCardAdded()
        CardsScreen(
            onCardAddClicked = {},
            cards = listOf(Card("1234-5678-9012-3456", "12/34", "홍길동", "1234")),
            viewModel = cardViewModel
        )
    }
}
