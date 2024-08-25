package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.dummyData

@Composable
fun PaymentCardInfo(
    card: Card,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = card.cardNumber.chunked(4).joinToString("-"),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = card.ownerName,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = card.expiredDate,
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