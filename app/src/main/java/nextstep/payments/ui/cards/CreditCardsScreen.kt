package nextstep.payments.ui.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.cards.component.CreditCardTopBar
import nextstep.payments.ui.cards.component.EmptyCardComponent
import nextstep.payments.ui.cards.component.ManyCardComponent
import nextstep.payments.ui.cards.component.OneCardComponent
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CreditCardsScreen(
    viewModel: CreditCardsViewModel,
    onAddClick: () -> Unit,
    onCardClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.creditCardState.collectAsStateWithLifecycle()
    CreditCardsScreen(
        state,
        onAddClick,
        onCardClick,
        modifier
    )
}

@Composable
private fun CreditCardsScreen(
    state: CreditCardUiState,
    onAddClick: () -> Unit,
    onCardClick: (Card) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CreditCardTopBar(
                isShowAddButton = state is CreditCardUiState.Many,
                onAddClick = onAddClick
            )
        }
    ) {
        val componentModifier = modifier
            .fillMaxSize()
            .padding(it)
        when (state) {
            is CreditCardUiState.Empty -> {
                EmptyCardComponent(
                    onAddClick = onAddClick,
                    modifier = componentModifier
                )
            }
            is CreditCardUiState.One -> {
                OneCardComponent(
                    card = state.card,
                    onAddClick = onAddClick,
                    modifier = componentModifier,
                    onCardClick = onCardClick
                )
            }
            is CreditCardUiState.Many -> {
                ManyCardComponent(
                    cards = state.cards,
                    modifier = componentModifier,
                    onCardClick = onCardClick
                )
            }
        }
    }
}

@Preview(name = "카드 0개")
@Composable
private fun CreditCardEmptyScreenPreview() {
    PaymentsTheme {
        CreditCardsScreen(
            state = CreditCardUiState.Empty,
            onAddClick = {},
            onCardClick = {}
        )
    }
}

@Preview(name = "카드 1개")
@Composable
private fun CreditCardOneScreenPreview() {
    PaymentsTheme {
        CreditCardsScreen(
            state = CreditCardUiState.One(
                card = Card(
                    cardNumber = "1111-1111-1111-1111",
                    expiredDate = "11 / 11",
                    ownerName = "컴포즈",
                    password = "1111",
                    cardCompany = CardCompany.KB
                )
            ),
            onAddClick = {},
            onCardClick = {}
        )
    }
}

@Preview(name = "카드 5개")
@Composable
private fun CreditCardManyScreenPreview() {
    PaymentsTheme {
        CreditCardsScreen(
            state = CreditCardUiState.Many(
                cards = listOf(
                    Card(id = 0,
                        cardNumber = "1111-1111-1111-1111",
                        expiredDate = "11 / 11",
                        ownerName = "컴포즈",
                        password = "1111",
                        cardCompany = CardCompany.HANA
                    ),
                    Card(id = 1,
                        cardNumber = "2222-2222-2222-2222",
                        expiredDate = "22 / 22",
                        ownerName = "김컴포즈",
                        password = "2222",
                        cardCompany = CardCompany.LOTTE
                    ),
                    Card(id = 2,
                        cardNumber = "3333-3333-3333-3333",
                        expiredDate = "33 / 33",
                        ownerName = "박컴포즈",
                        password = "3333",
                        cardCompany = CardCompany.BC
                    ),
                    Card(id = 3,
                        cardNumber = "4444-4444-4444-4444",
                        expiredDate = "44 / 44",
                        ownerName = "최컴포즈",
                        password = "4444",
                        cardCompany = CardCompany.WOORI
                    ),
                    Card(id = 4,
                        cardNumber = "5555-5555-5555-5555",
                        expiredDate = "55 / 55",
                        ownerName = "이컴포즈",
                        password = "5555",
                        cardCompany = CardCompany.SHINHAN
                    ),
                )
            ),
            onAddClick = {},
            onCardClick = {}
        )
    }
}