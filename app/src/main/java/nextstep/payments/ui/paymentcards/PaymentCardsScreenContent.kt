package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.CreditCardUiState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardsScreenContent(
    uiState: CreditCardUiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    when (uiState) {
        is CreditCardUiState.Empty -> EmptyCardScreen(
            modifier = modifier,
            onAddClick = onAddClick
        )

        is CreditCardUiState.One -> OneCardScreen(
            card = uiState.card,
            modifier = modifier,
            onAddClick = onAddClick
        )

        is CreditCardUiState.Many -> ManyCardsScreen(
            cards = uiState.cards,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun EmptyPaymentCardPreview() {
    PaymentsTheme {
        PaymentCardsScreenContent(
            uiState = CreditCardUiState.Empty,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun OnePaymentCardPreview() {
    PaymentsTheme {
        PaymentCardsScreenContent(
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
        PaymentCardsScreenContent(
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