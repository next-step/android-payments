package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.PaymentCardViewModel
import nextstep.payments.ui.CreditCardUiState

@Composable
fun PaymentCardsScreen(
    viewModel: PaymentCardViewModel,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    val uiState by viewModel.cardUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PaymentCardsTopBar(
                onAddClick = onAddClick,
                isAddButtonVisible = uiState is CreditCardUiState.Many
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        PaymentCardsScreenContent(
            uiState = uiState,
            modifier = modifier.padding(innerPadding),
            onAddClick = onAddClick
        )
    }
}