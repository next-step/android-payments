package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.BcCard
import nextstep.payments.data.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardImage(card: Card, cardColor: Color, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
        ),
        modifier = modifier
    ) {
        IntegratedCircuit(
            modifier = Modifier
                .padding(top = 44.dp, start = 14.dp)
                .size(width = 40.dp, height = 26.dp)
        )
        CardNumber(
            cardNumber = card.cardNumber,
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
        )

        Row {
            CardOwnerName(
                ownerName = card.ownerName,
                modifier = Modifier.padding(start = 14.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            CardExpiredDate(
                expiredDate = card.expiredDate,
                modifier = Modifier.padding(end = 14.dp)
            )
        }
    }
}


@Preview
@Composable
private fun CardImagePreview() {
    PaymentsTheme {
        CardImage(
            Card(
                cardNumber = "1234-5678-1234-5678",
                ownerName = "홍길동",
                expiredDate = "12/34",
                password = "123",
                cardCompany = BcCard
            ),
            cardColor = Color(0xff333333),
            modifier = Modifier
                .size(width = 208.dp, height = 124.dp)
        )
    }
}
