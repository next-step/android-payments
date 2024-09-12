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
import nextstep.payments.data.model.BankType
import nextstep.payments.data.model.CreditCard
import nextstep.payments.screen.model.CreditCardUiModel
import nextstep.payments.screen.model.toUiModel
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    navigateToAddCard: () -> Unit,
    navigateToEditCard: (CreditCardUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val creditCardUiState by viewModel.cardListUiState.collectAsStateWithLifecycle()

    CardListScreen(
        modifier = modifier,
        navigateToAddCard = navigateToAddCard,
        navigateToEditCard = navigateToEditCard,
        creditCardUiState = creditCardUiState
    )
}

@Composable
fun CardListScreen(
    creditCardUiState: CreditCardUiState,
    navigateToAddCard: () -> Unit,
    navigateToEditCard: (CreditCardUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardListTopBar(
                actions = {
                    if(creditCardUiState is CreditCardUiState.Many){
                        TextButton(
                            onClick = navigateToAddCard
                        ) {
                            Text(
                                text = stringResource(id = R.string.card_list_add),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black
                            )
                        }
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
            when (creditCardUiState) {
                is CreditCardUiState.Empty -> {
                    item {
                        AdditionCardText()
                    }
                }

                is CreditCardUiState.One -> {
                    item {
                        PaymentCard(
                            card = creditCardUiState.card,
                            onClick = navigateToEditCard
                        )
                    }
                }

                is CreditCardUiState.Many -> {
                    items(creditCardUiState.cards) { card ->
                        PaymentCard(
                            card = card,
                            onClick = navigateToEditCard
                        )
                    }
                }
            }
            if (creditCardUiState !is CreditCardUiState.Many) {
                item {
                    AdditionCard(
                        onClick = navigateToAddCard
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
            navigateToEditCard = {},
            navigateToAddCard = {}
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
                    cardNumber = "1234123412341234",
                    ownerName = "CREW",
                    expiredDate = "0421",
                    password = "1234",
                    bankType = BankType.BC
                ).toUiModel()
            ),
            navigateToEditCard = {},
            navigateToAddCard = {}
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
                        cardNumber = "1234123412341234",
                        ownerName = "CREW",
                        expiredDate = "0421",
                        password = "1234",
                        bankType = BankType.BC
                    ),
                    CreditCard(
                        cardNumber = "1234123412341234",
                        ownerName = "CREW",
                        expiredDate = "0421",
                        password = "1234",
                        bankType = BankType.KAKAO
                    ),
                    CreditCard(
                        cardNumber = "1234123412341234",
                        ownerName = "CREW",
                        expiredDate = "0421",
                        password = "1234",
                        bankType = BankType.HANA
                    ),
                    CreditCard(
                        cardNumber = "1234123412341234",
                        ownerName = "CREW",
                        expiredDate = "0421",
                        password = "1234",
                        bankType = BankType.SHINHAN
                    )
                ).map { it.toUiModel() }
            ),
            navigateToEditCard = {},
            navigateToAddCard = {}
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
