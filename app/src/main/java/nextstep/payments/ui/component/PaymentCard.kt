package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun PaymentCard(
    modifier: Modifier = Modifier,
) {
    PaymentCardFrame(modifier)
}

@Composable
internal fun PaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    PaymentCardFrame(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            Text(
                text = card.number,
                style = textStyle,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card.ownerName,
                    style = textStyle,
                )
                Text(
                    text = card.expiredDate,
                    style = textStyle,
                )
            }
        }
    }
}

@Composable
private fun PaymentCardFrame(
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        content()
    }
}

@Preview
@Composable
private fun PaymentCardPreview_empty() {
    PaymentsTheme {
        PaymentCard()
    }
}

@Preview
@Composable
private fun PaymentCardPreview_card() {
    PaymentsTheme {
        PaymentCard(
            card = Card(
                number = "0000 - 0000 - 0000 - 0000",
                expiredDate = "00 / 00",
                ownerName = "홍길동",
                password = "0000"
            )
        )
    }
}
