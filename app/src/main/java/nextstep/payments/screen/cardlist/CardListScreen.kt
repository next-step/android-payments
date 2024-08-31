package nextstep.payments.screen.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.component.card.AdditionCard
import nextstep.payments.component.card.PaymentCard
import nextstep.payments.component.topbar.CardListTopBar
import nextstep.payments.data.model.CreditCard
import nextstep.payments.screen.model.toUiModel
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    navigateToNewCard : () -> Unit,
    modifier: Modifier = Modifier
) {
    val creditCardUiState by viewModel.cardListUiState.collectAsStateWithLifecycle()

    CardListScreen(
        modifier = modifier,
        navigateToNewCard = navigateToNewCard,
        creditCardUiState = creditCardUiState
    )
}

@Composable
fun CardListScreen(
    creditCardUiState: CreditCardUiState,
    navigateToNewCard : () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardListTopBar(
                actions = {
                    TextButton(
                        onClick = navigateToNewCard
                    ) {
                        Text(
                            text = stringResource(id = R.string.card_list_add),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(creditCardUiState){
                is CreditCardUiState.Empty -> {
                    item {
                        AdditionCardText()
                    }
                }
                is CreditCardUiState.One -> {
                    item {
                        PaymentCard(
                            cardNumber = stringResource(
                                id = R.string.card_number,
                                creditCardUiState.card.firstCardDigits,
                                creditCardUiState.card.secondCardDigits
                            ),
                            ownerName = creditCardUiState.card.ownerName,
                            expiredDate = stringResource(
                                id = R.string.expired_date,
                                creditCardUiState.card.month,
                                creditCardUiState.card.year
                            )
                        )
                    }
                }
                is CreditCardUiState.Many -> {
                    items(creditCardUiState.cards){ card ->
                        PaymentCard(
                            cardNumber = stringResource(id = R.string.card_number,card.firstCardDigits,card.secondCardDigits),
                            ownerName = card.ownerName,
                            expiredDate = stringResource(
                                id = R.string.expired_date,
                                card.month,
                                card.year
                            )
                        )
                    }
                }
            }
            if(creditCardUiState !is CreditCardUiState.Many){
                item {
                    AdditionCard(
                        onClick = navigateToNewCard
                    )
                }
            }
        }
    }
}

@Composable
fun AdditionCardText(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.card_list_item_add),
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF333333)
    )
}

@Preview(showBackground = true, name = "CardListScreen")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardListScreen(
            viewModel = CardListViewModel(),
            navigateToNewCard = {}
        )
    }
}

@Preview(showBackground = true, name = "CardListScreenWithACard")
@Composable
private fun Preview2() {
    PaymentsTheme {
        CardListScreen(
            creditCardUiState = CreditCardUiState.One(
                CreditCard(
                    cardNumber = "1234 - 1234 - 1234 - 1234",
                    ownerName = "CREW",
                    expiredDate = "04 / 21",
                    password = "1234"
                ).toUiModel()
            ),
            navigateToNewCard = {}
        )
    }
}

@Preview(showBackground = true, name = "CardListScreenWithCards")
@Composable
private fun Preview3() {
    PaymentsTheme {
        CardListScreen(
            creditCardUiState = CreditCardUiState.Many(
                cards = listOf(
                    CreditCard(
                        cardNumber = "1234 - 1234 - 1234 - 1234",
                        ownerName = "CREW",
                        expiredDate = "04 / 21",
                        password = "1234"
                    ),
                    CreditCard(
                        cardNumber = "1234 - 1234 - 1234 - 1234",
                        ownerName = "CREW1",
                        expiredDate = "04 / 21",
                        password = "1234"
                    ),
                    CreditCard(
                        cardNumber = "1234 - 1234 - 1234 - 1234",
                        ownerName = "CREW2",
                        expiredDate = "04 / 21",
                        password = "1234"
                    ),
                    CreditCard(
                        cardNumber = "1234 - 1234 - 1234 - 1234",
                        ownerName = "CREW3",
                        expiredDate = "04 / 21",
                        password = "1234"
                    )
                ).map { it.toUiModel() }
            ),
            navigateToNewCard = {}
        )
    }
}

@Preview(showBackground = true, name = "AdditionCardText")
@Composable
private fun Preview4() {
    PaymentsTheme {
        AdditionCardText()
    }
}