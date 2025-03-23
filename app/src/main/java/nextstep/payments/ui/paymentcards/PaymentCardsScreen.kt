package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nextstep.payments.ui.CreditCardUiState
import nextstep.payments.ui.newcard.PaymentCardsTopBar

@Composable
fun PaymentCardsScreen(
    uiState: CreditCardUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    Scaffold(
        topBar = { PaymentCardsTopBar() },
        modifier = modifier
    ) { innerPadding ->
        when (uiState) {
            is CreditCardUiState.Empty -> EmptyCardScreen(
                modifier.padding(innerPadding),
                onAddClick
            )

            is CreditCardUiState.One -> OneCardScreen(
                card = uiState.card,
                modifier.padding(innerPadding), onAddClick
            )

            is CreditCardUiState.Many -> ManyCardsScreen(
                cards = uiState.cards,
                modifier.padding(innerPadding), onAddClick
            )
        }
    }
}