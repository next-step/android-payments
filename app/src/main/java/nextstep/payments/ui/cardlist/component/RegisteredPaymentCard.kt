package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.CardState.Card
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.body

@Composable
fun RegisteredPaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(
                width = 208.dp,
                height = 124.dp,
            )
            .semantics { contentDescription = "RegisteredPaymentCard" },
    ) {
        PaymentCard()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 78.dp),
        ) {
            CardNumberTextField(
                firstOfCardNumber = card.firstOfCardNumber,
                secondOfCardNumber = card.secondOfCardNumber,
            )
            Spacer(modifier = Modifier.height(height = 2.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = card.ownerName,
                    style = body,
                )
                Text(
                    text = card.formattedExpiredDate,
                    style = body,
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisteredPaymentCardPreview() {
    RegisteredPaymentCard(
        card = Card(
            cardId = 0L,
            cardNumber = "1234123412341234",
            expiredDate = "04 / 21",
            ownerName = "sehun",
            password = ""
        )
    )
}
