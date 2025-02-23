package nextstep.payments.creditcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.creditcard.component.CreditCardEmpty
import nextstep.payments.creditcard.component.CreditCardMany
import nextstep.payments.creditcard.component.CreditCardOne
import nextstep.payments.creditcard.model.CreditCardUiState
import nextstep.payments.ui.theme.common.component.Loading


@Composable
fun CreditCardScreen(
    onNavigateToNewCard: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreditCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CreditCardContent(
        uiState = uiState,
        onNavigateToNewCard = { onNavigateToNewCard() },
        modifier = modifier
    )
}

@Composable
private fun CreditCardContent(
    uiState: CreditCardUiState,
    onNavigateToNewCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        CreditCardUiState.Empty -> CreditCardEmpty(
            onNavigateToNewCard = { onNavigateToNewCard() },
            modifier = modifier
        )

        is CreditCardUiState.One -> CreditCardOne(
            onNavigateToNewCard = { onNavigateToNewCard() },
            card = uiState.card,
            modifier = modifier
        )

        is CreditCardUiState.Many -> CreditCardMany(
            onNavigateToNewCard = { onNavigateToNewCard() },
            cards = uiState.cards,
            modifier = modifier
        )

        CreditCardUiState.Loading -> Loading()
    }
}
