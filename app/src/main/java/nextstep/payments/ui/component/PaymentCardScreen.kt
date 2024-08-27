package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    cardCompany: String,
    modifier: Modifier = Modifier,
    cardColor: Color = Color(0xFF333333),
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = cardColor,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Text(
            text = cardCompany,
            style = MaterialTheme.typography.bodySmall,
            color = if (cardColor.luminance() > 0.5) Color.Black else Color.White,
            modifier = Modifier
                .padding(start = 14.dp, top = 15.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(cardCompany = "롯데카드", cardColor = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardBlackPaymentCardPreviewPreview() {
    PaymentsTheme {
        PaymentCard(cardCompany = "롯데카드", cardColor = Color.Black)
    }
}