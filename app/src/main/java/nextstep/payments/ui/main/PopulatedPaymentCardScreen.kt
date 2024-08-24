package nextstep.payments.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card
import nextstep.payments.ui.component.MaskedCardNumberText
import nextstep.payments.ui.component.PaymentCard

@Composable
fun PopulatedPaymentCard(
    card: Card,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(208.dp)
            .height(124.dp)
    ) {
        PaymentCard(
            cardCompany = card.cardCompany,
            cardColor = card.cardColor
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            MaskedCardNumberText(
                cardNumber = card.cardNumber,
                cardColor = card.cardColor,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = card.ownerName.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (card.cardColor.luminance() > 0.5) Color.Black else Color.White
                )
                Text(
                    text = card.expiredDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (card.cardColor.luminance() > 0.5) Color.Black else Color.White,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview
@Composable
private fun PopulatedPaymentCardPreview() {
    PopulatedPaymentCard(
        card = Card(
            "1234-5678-1234-5678",
            "12/23",
            "yoon",
            "1234",
            cardCompany = "롯데카드",
            cardColor = Color.White,
        )
    )
}
