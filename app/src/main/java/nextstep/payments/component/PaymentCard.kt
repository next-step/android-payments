package nextstep.payments.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.consts.CardConstant

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardNumber: String? = null,
    expiredDate: String? = null,
    ownerName: String? = null,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = CardConstant.CARD_WIDTH.dp, height = CardConstant.CARD_HEIGHT.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
            .padding(14.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart)
        )
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            ProvideTextStyle(
                value = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                )
            ) {
                if (!cardNumber.isNullOrBlank()) {
                    Text(text = cardNumber)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    if (!ownerName.isNullOrBlank()) {
                        Text(text = ownerName)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (!expiredDate.isNullOrBlank()) {
                        Text(text = expiredDate)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard()
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreviewHasInfo() {
    PaymentCard(
        cardNumber = "1234-5678-9012-3456",
        expiredDate = "12/25",
        ownerName = "SeokJun Jeong",
    )
}
