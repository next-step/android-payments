package nextstep.payments.card.component.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.card.Card
import nextstep.payments.card.component.AddingCardSlot
import nextstep.payments.card.component.PaymentCard

@Composable
fun SingleCardContent(
    card: Card,
    onCardClick: (Card) -> Unit,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard(
            modifier = Modifier.clickable(onClick = { onCardClick(card) }),
            cardColor = Color(0xFF333333),
            card = card,
        )

        Spacer(modifier = Modifier.height(36.dp))

        AddingCardSlot(onClick = onAddCardClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleCardContentPreview() {
    SingleCardContent(
        card = Card(
            cardNumber = "1111 - 2222 - 3333 - 4444",
            expireDate = "04/21",
            ownerName = "Crew",
            cvcNumber = "000",
            password = "1234",
        ),
        onCardClick = {},
        onAddCardClick = {},
    )
}
