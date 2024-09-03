package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.dummyData
import nextstep.payments.ui.newcard.model.BankUI

@Composable
fun PaymentCardInfo(
    card: Card,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxHeight()) {
        Text(
            text = BankUI.fromBank(card.bank)?.bankName ?: "",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 14.dp, top = 10.dp)
        )
        Text(text = card.cardNumber.chunked(4).joinToString("-"), style = MaterialTheme.typography.bodySmall, color = Color.White, modifier = Modifier
                .size(width = 208.dp, height = 124.dp)
                .padding(start = 14.dp, end = 14.dp, top = 75.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, top = 95.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = card.ownerName,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = card.expiredDate.chunked(2).joinToString("/"),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }

    }
}

@Preview
@Composable
private fun PaymentCardInfoPreview() {
    PaymentCardInfo(dummyData)
}
