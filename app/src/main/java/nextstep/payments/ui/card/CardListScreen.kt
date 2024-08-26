package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.components.AddPaymentCard
import nextstep.payments.ui.components.PaymentActionsTopBar
import nextstep.payments.ui.components.PaymentTopBar
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardViewModel,
    onAddPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val creditCardUiState by viewModel.creditCardUiState.collectAsStateWithLifecycle()

    CardListScreen(
        creditCardUiState = creditCardUiState,
        onAddPaymentCard = onAddPaymentCard,
        modifier = modifier
    )
}

@Composable
fun CardListScreen(
    creditCardUiState: CreditCardUiState,
    onAddPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            when (creditCardUiState) {
                is CreditCardUiState.Empty, is CreditCardUiState.One -> {
                    PaymentTopBar(title = stringResource(id = R.string.card_list_top_bar_title))
                }

                is CreditCardUiState.Many -> {
                    PaymentActionsTopBar(
                        title = stringResource(id = R.string.card_list_top_bar_title),
                        onAddPaymentCard = onAddPaymentCard
                    )
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (creditCardUiState) {
                is CreditCardUiState.Empty -> {
                    Text(
                        text = stringResource(id = R.string.add_new_card_description),
                        style = MaterialTheme.typography.titleMedium
                    )
                    AddPaymentCard(
                        onAddPaymentCard = onAddPaymentCard,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }

                is CreditCardUiState.One -> {
                    val card = creditCardUiState.card
                    PaymentCard(card = card)
                    AddPaymentCard(
                        onAddPaymentCard = onAddPaymentCard,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }

                is CreditCardUiState.Many -> {
                    val cards = creditCardUiState.cards
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(36.dp)
                    ) {
                        items(cards) { card ->
                            PaymentCard(card = card)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview() {
    PaymentsTheme {
        CardListScreen(
            viewModel = viewModel(),
            onAddPaymentCard = {

            }
        )
    }
}
