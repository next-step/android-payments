package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.CreditCardUiState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardsScreen(
    uiState: CreditCardUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    Scaffold(
        topBar = { PaymentCardsTopBar(uiState = uiState) },
        modifier = modifier
    ) { innerPadding ->
        when (uiState) {
            is CreditCardUiState.Empty -> EmptyCardScreen(
                modifier.padding(innerPadding),
                onAddClick
            )

            is CreditCardUiState.One -> OneCardScreen(
                card = uiState.card,
                modifier.padding(innerPadding),
                onAddClick
            )

            is CreditCardUiState.Many -> ManyCardsScreen(
                cards = uiState.cards,
                modifier.padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
private fun EmptyPaymentCardPreview() {
    PaymentsTheme {
        PaymentCardsScreen(
            uiState = CreditCardUiState.Empty,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun OnePaymentCardPreview() {
    PaymentsTheme {
        PaymentCardsScreen(
            uiState = CreditCardUiState.One(
                CreditCard(
                    "1111222233334444",
                    "0421",
                    "crew",
                    "1111"
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun ManyPaymentCardsPreview() {
    PaymentsTheme {
        PaymentCardsScreen(
            uiState = CreditCardUiState.Many(
                listOf(
                    CreditCard("1111222233334444", "0421", "crew", "1111"),
                    CreditCard("1111222233334444", "0421", "crew", "1111"),
                    CreditCard("1111222233334444", "0421", "crew", "1111"),
                    CreditCard("1111222233334444", "0421", "crew", "1111")
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}