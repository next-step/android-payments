package nextstep.payments.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.common.component.EmptyCard
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.common.model.CardCompany
import nextstep.payments.common.model.Card

@Composable
fun OneCardScreen(
    card: Card,
    onClickCard: (id: Int) -> Unit,
    onClickAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        PaymentCard(
            card = card,
            modifier = Modifier.clickable { onClickCard(card.id) }
        )
        EmptyCard(
            modifier = Modifier.clickable { onClickAddCard() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OneCardScreenPreview() {
    OneCardScreen(
        card = Card(
            id = 0,
            cardCompany = CardCompany.HYUNDAI,
            cardNumber = "11112222********",
            expiredDate = "12/25",
            ownerName = "CREW",
            password = "1234",
        ),
        onClickAddCard = {},
        onClickCard = {}
    )
}
