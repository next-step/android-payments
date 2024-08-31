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
    modifier: Modifier = Modifier,
) {
    val state by viewModel.creditCardState.collectAsStateWithLifecycle()
    CreditCardsScreen(
        state,
        onAddClick,
        modifier
    )
}

@Composable
private fun CreditCardsScreen(
    state: CreditCardUiState,
    onAddClick: () -> Unit,
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
                    modifier = componentModifier
                )
            }
            is CreditCardUiState.Many -> {
                ManyCardComponent(
                    cards = state.cards,
                    modifier = componentModifier
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
            onAddClick = {}
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
                    cardCompany = CardCompany.NOT_SELECTED
                )
            ),
            onAddClick = {}
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
                    Card("1111-1111-1111-1111", "11 / 11", "컴포즈", "1111", CardCompany.NOT_SELECTED),
                    Card("2222-2222-2222-2222", "22 / 22", "김컴포즈", "2222", CardCompany.NOT_SELECTED),
                    Card("3333-3333-3333-3333", "33 / 33", "박컴포즈", "3333", CardCompany.NOT_SELECTED),
                    Card("4444-4444-4444-4444", "44 / 44", "최컴포즈", "4444", CardCompany.NOT_SELECTED),
                    Card("5555-5555-5555-5555", "55 / 55", "이컴포즈", "5555", CardCompany.NOT_SELECTED),
                )
            ),
            onAddClick = {}
        )
    }
}