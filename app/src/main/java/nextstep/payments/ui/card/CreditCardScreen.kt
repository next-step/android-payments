package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.Card
import nextstep.payments.ui.component.CreditCardItem
import nextstep.payments.ui.component.EmptyCardItem
import nextstep.payments.ui.ext.cardDefaultSize
import nextstep.payments.ui.theme.TopBarTitleColor


@Composable
fun CreditCardScreen(
    viewModel: CreditCardViewModel = viewModel(),
) {
    val creditCardUiState by viewModel.creditCardUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardListTopAppBar(creditCardUiState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreditCardScreen(creditCardUiState)
        }
    }
}

@Composable
fun CreditCardScreen(
    uiState: CreditCardUiState
) {
    when (uiState) {
        is CreditCardUiState.Empty -> EmptyCardScreen()
        is CreditCardUiState.One -> SingleCardScreen(uiState.card)
        is CreditCardUiState.Many -> CardListScreen(uiState.cards)
    }
}

@Composable
fun EmptyCardScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.register_new_card),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        EmptyCardItem(
            onClickItem = {},
            modifier = Modifier.cardDefaultSize()
        )
    }
}

@Composable
fun SingleCardScreen(card: Card) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        CreditCardItem(
            cardNumber = card.cardNumber,
            cardOwnerName = card.cardOwnerName,
            cardExpiredDate = card.cardExpiredDate
        )
        EmptyCardItem(
            onClickItem = { /*TODO*/ },
            modifier = Modifier.cardDefaultSize()
        )
    }
}

@Composable
fun CardListScreen(cards: List<Card>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = cards,
            key = { it.cardNumber }
        ) { item ->
            CreditCardItem(
                cardNumber = item.cardNumber,
                cardOwnerName = item.cardOwnerName,
                cardExpiredDate = item.cardExpiredDate
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppBar(
    uiState: CreditCardUiState
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.payments),
                color = TopBarTitleColor,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            if (uiState is CreditCardUiState.Many) {
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardScreenPreview() {
    EmptyCardScreen()
}

@Preview(showBackground = true)
@Composable
private fun SingleCardScreenPreview() {
    val uiState = CreditCardUiState.One(
        Card(
            cardNumber = "1111 - 2222 - **** - ****",
            cardOwnerName = "Park",
            cardExpiredDate = "04 / 21"
        )
    )
    SingleCardScreen(uiState.card)
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview() {
    val uiState = CreditCardUiState.Many(
        listOf(
            Card(
                cardNumber = "1111 - 2222 - **** - ****",
                cardOwnerName = "Park",
                cardExpiredDate = "04 / 21"
            ),
            Card(
                cardNumber = "1111 - 2234 - **** - ****",
                cardOwnerName = "Park",
                cardExpiredDate = "04 / 21"
            )
        )
    )
    CardListScreen(uiState.cards)
}

@Preview(showBackground = true)
@Composable
private fun CardListTopAppBarPreview() {
    val uiState = CreditCardUiState.Empty
    CardListTopAppBar(uiState)
}
