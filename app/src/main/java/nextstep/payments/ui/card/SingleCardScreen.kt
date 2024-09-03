package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.component.CreditCardItem
import nextstep.payments.ui.component.EmptyCardItem


@Composable
fun SingleCardScreen(
    card: Card,
    onClickAddItem: () -> Unit,
    onClickCard: (Card) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        CreditCardItem(
            card = card,
            onClickCard = { onClickCard(card) }
        )
        EmptyCardItem(onClickItem = onClickAddItem)
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleCardScreenPreview() {
    val uiState = CreditCardUiState.One(
        Card(
            cardNumber = "1111 - 2222 - **** - ****",
            cardOwnerName = "Park",
            cardExpiredDate = "04 / 21",
            cardPassword = "1234",
            bankType = BankType.SHINHAN
        )
    )
    SingleCardScreen(uiState.card, {}, {})
}
