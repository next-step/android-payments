package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.ext.cardDefaultSize
import nextstep.payments.ui.theme.CardChipColor


@Composable
fun CreditCardItem(
    card: Card,
    onClickCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .cardDefaultSize()
            .clip(RoundedCornerShape(5.dp))
            .background(card.bankType.color)
            .clickable { onClickCard() }
            .padding(horizontal = 14.dp, vertical = 16.dp)
    ) {
        Column(
            Modifier.align(Alignment.BottomStart)
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
                    text = card.cardNumber,
                    modifier = Modifier.fillMaxWidth(),
                    letterSpacing = 1.7.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = card.cardOwnerName,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = card.cardExpiredDate,
                        letterSpacing = 0.8.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardItemPreview(
    @PreviewParameter(CardListPreviewParameterProvider::class) value: Card
) {
    Box {
        CreditCardItem(
            card = value,
            onClickCard = {}
        )
    }
}

private class CardListPreviewParameterProvider : CollectionPreviewParameterProvider<Card>(
    listOf(
        Card(
            id = 0,
            cardNumber = "0000 - 1111 - **** - ****",
            cardExpiredDate = "08/27",
            cardOwnerName = "Park",
            cardPassword = "1234",
            bankType = BankType.SHINHAN
        ),
        Card(
            id = 1,
            cardNumber = "0000 - 2222 - **** - ****",
            cardExpiredDate = "08/27",
            cardOwnerName = "Park",
            cardPassword = "1234",
            bankType = BankType.LOTTE
        ),
        Card(
            id = 2,
            cardNumber = "0000 - 2222 - **** - ****",
            cardExpiredDate = "08/27",
            cardOwnerName = "Park",
            cardPassword = "1234",
            bankType = BankType.KAKAO
        )
    )
)
