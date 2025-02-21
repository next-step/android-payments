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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.CardModel
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun Card(model: CardModel) {
    Card(backgroundColor = Color(0xFF333333)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp)
                .align(Alignment.BottomCenter),
        )
        {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = model.number,
                style = Typography.bodyMedium,
                color = Color.White,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = model.ownerName,
                    style = Typography.bodySmall,
                    color = Color.White,
                )
                Text(
                    text = model.expiredDate,
                    style = Typography.bodyMedium,
                    color = Color.White,
                )
            }
        }
    }
}

private fun String.toCardNumberFormat(): String {
    return ""
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE5E5E5),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp),
            ),
    ) {
        content()
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        Card(
            model = CardModel(
                number = "1111 - 2222 - **** - ****",
                ownerName = "CREW",
                password = "Rebbecca",
                expiredDate = "04 / 21"
            )
        )
    }
}