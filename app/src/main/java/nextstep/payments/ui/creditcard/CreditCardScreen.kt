package nextstep.payments.ui.creditcard

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.creditcard.component.CreditCardEmpty
import nextstep.payments.ui.creditcard.component.CreditCardMany
import nextstep.payments.ui.creditcard.component.CreditCardOne
import nextstep.payments.ui.creditcard.component.CreditCardTopBar
import nextstep.payments.ui.creditcard.model.CreditCardUiState
import nextstep.payments.ui.component.Loading


@Composable
fun CreditCardScreen(
    onNavigateToNewCard: (ManagedActivityResultLauncher<Intent, androidx.activity.result.ActivityResult>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreditCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                viewModel.getCards()
            }
        }
    CreditCardScreen(
        uiState = uiState,
        onNavigateToNewCard = { onNavigateToNewCard(launcher) },
        modifier = modifier
    )
}

@Composable
fun CreditCardScreen(
    uiState: CreditCardUiState,
    onNavigateToNewCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        CreditCardTopBar(uiState = uiState, onNavigateToNewCard = onNavigateToNewCard)
        CreditCardContent(
            uiState = uiState,
            onNavigateToNewCard = onNavigateToNewCard,
        )
    }
}

@Composable
fun CreditCardContent(
    uiState: CreditCardUiState,
    onNavigateToNewCard: () -> Unit,

    ) {
    when (uiState) {
        CreditCardUiState.Empty -> CreditCardEmpty(
            onNavigateToNewCard = { onNavigateToNewCard() },

        )

        is CreditCardUiState.One -> CreditCardOne(
            onNavigateToNewCard = { onNavigateToNewCard() },
            card = uiState.card,

        )

        is CreditCardUiState.Many -> CreditCardMany(
            cards = uiState.cards,

        )

        CreditCardUiState.Loading -> Loading()
    }
}
