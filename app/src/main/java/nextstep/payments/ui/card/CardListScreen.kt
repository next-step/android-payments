package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.component.CreditCardItem


@Composable
fun CardListScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = cards,
            key = { it.cardNumber }
        ) { item ->
            CreditCardItem(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview() {
    val cardNumbers = listOf(
        "1111 - 2222 - **** - ****",
        "1111 - 2234 - **** - ****",
        "1111 - 1112 - **** - ****",
        "1111 - 1113 - **** - ****",
        "1111 - 1114 - **** - ****",
        "1111 - 1115 - **** - ****"
    )
    val cards = cardNumbers.mapIndexed { index, number ->
        Card(
            cardNumber = number,
            cardOwnerName = "Park",
            cardExpiredDate = "04 / 21",
            bankType = BankType.entries[index]
        )
    }
    val uiState = CreditCardUiState.Many(cards)
    CardListScreen(uiState.cards)
}
