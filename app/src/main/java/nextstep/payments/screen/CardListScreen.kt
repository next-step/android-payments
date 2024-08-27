package nextstep.payments.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    navigateToNewCard : () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardList by viewModel.creditCardList.collectAsStateWithLifecycle()

    CardListScreen(
        modifier = modifier,
        navigateToNewCard = navigateToNewCard,
        creditCardList = cardList
    )
}

@Composable
fun CardListScreen(
    creditCardList: List<CreditCard>,
    navigateToNewCard : () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardListTopBar(
                onSaveClick = navigateToNewCard,
                isShownAddText = creditCardList.size > 3
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
            if(creditCardList.isEmpty()){
                item {
                    AdditionCardText()
                }
            }
            if(creditCardList.isNotEmpty()){
                items(creditCardList){ card ->
                    PaymentCard(
                        cardNumber = stringResource(id = R.string.card_number,card.firstCardDigits,card.secondCardDigits),
                        ownerName = card.ownerName,
                        expiredDate = card.expiredDate
                    )
                }
            }
            if(creditCardList.size <= 3){
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
            creditCardList = listOf(
                CreditCard(
                    cardNumber = "1234 - 1234 - 1234 - 1234",
                    ownerName = "CREW",
                    expiredDate = "04 / 21",
                    password = "1234"
                )
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
            creditCardList = listOf(
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