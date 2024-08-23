package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.ext.cardDefaultSize
import nextstep.payments.ui.theme.CardBgColor
import nextstep.payments.ui.theme.CardChipColor


@Composable
fun CreditCard(
    cardNumber: String,
    cardOwnerName: String,
    cardExpiredDate: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .shadow(8.dp)
            .cardDefaultSize()
            .background(CardBgColor)
            .clip(RoundedCornerShape(5.dp))
            .padding(horizontal = 14.dp, vertical = 16.dp)
    ) {
        Column(
            modifier.align(Alignment.BottomStart)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp, 26.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(CardChipColor)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CompositionLocalProvider(
                LocalContentColor provides Color.White,
                LocalTextStyle provides MaterialTheme.typography.labelMedium
            ) {
                Text(
                    text = cardNumber,
                    modifier = Modifier.fillMaxWidth(),
                    letterSpacing = 1.7.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cardOwnerName,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = cardExpiredDate,
                        letterSpacing = 0.8.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardPreview() {
    Box {
        CreditCard(
            cardNumber = "1111 - 2222 - **** - ****",
            cardOwnerName = "Park",
            cardExpiredDate = "04 / 21"
        )
    }
}
