package nextstep.payments.ui.creditcard

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.creditcard.component.CreditCardEmpty
import nextstep.payments.ui.creditcard.component.CreditCardMany
import nextstep.payments.ui.creditcard.component.CreditCardOne
import nextstep.payments.ui.creditcard.component.CreditCardTopBar
import nextstep.payments.ui.creditcard.model.CreditCardUiState
import nextstep.payments.ui.component.Loading
import nextstep.payments.ui.model.CreditCardType
import nextstep.payments.ui.newcard.model.CardCompany


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
            modifier = Modifier.fillMaxSize()

        )

        is CreditCardUiState.One -> CreditCardOne(
            onNavigateToNewCard = { onNavigateToNewCard() },
            card = uiState.card,
            modifier = Modifier.fillMaxSize()

        )

        is CreditCardUiState.Many -> CreditCardMany(
            cards = uiState.cards,
            modifier = Modifier.fillMaxSize()

        )

        CreditCardUiState.Loading -> Loading()
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardScreenPreview() {
    CreditCardScreen(
        uiState = CreditCardUiState.Many(
            listOf(
                CreditCardType.RegisteredCard(
                    "1234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardCompany = CardCompany(R.drawable.ic_shinhan, "신한카드", 0xFF0078FF),
                ),  CreditCardType.RegisteredCard(
                    "1234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardCompany = CardCompany(R.drawable.ic_shinhan, "신한카드", 0xFF0078FF),
                ),  CreditCardType.RegisteredCard(
                    "1234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardCompany = CardCompany(R.drawable.ic_shinhan, "신한카드", 0xFF0078FF),
                ),  CreditCardType.RegisteredCard(
                    "1234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardCompany = CardCompany(R.drawable.ic_shinhan, "신한카드", 0xFF0078FF),
                )
            )
        ), onNavigateToNewCard = ({})
    )
}
