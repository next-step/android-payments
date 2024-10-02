package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.Card

@Composable
fun PaymentCardBottomField(card: Card) {
    Column(
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Start,
                style = TextStyle(letterSpacing = 3.sp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = card.ownerName,
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                text = card.expiredDate,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}
