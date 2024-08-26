package nextstep.payments.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.text.CardNumberText
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.utils.backgroundColor
import nextstep.payments.ui.utils.toFormattedExpirationDate

@Composable
fun PaymentCard(
    cardNumber: String,
    cardOwnerName: String,
    cardExpiredDate: String,
    bankType: BankTypeModel,
    modifier: Modifier = Modifier,
) {
    val cardContentDescription = stringResource(
        id = R.string.payment_card_content_description,
        bankType.companyName,
        cardOwnerName,
        cardNumber.chunked(4).joinToString(" "),
        cardExpiredDate.toFormattedExpirationDate(
            maxLength = 4,
            separator = "/"
        )
    )
    PaymentCardLayout(
        modifier = modifier.semantics {
            contentDescription = cardContentDescription
            this.backgroundColor = bankType.color
        },
        colors = PaymentCardLayoutDefaults.colors(containerColor = bankType.color)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.labelMedium
            ) {
                CardNumberText(
                    modifier = Modifier.fillMaxWidth(),
                    cardNumber = cardNumber,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 2.dp)
                ) {
                    Text(text = cardOwnerName)
                    Text(
                        text = cardExpiredDate.toFormattedExpirationDate(
                            maxLength = 4,
                            separator = "/"
                        ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            cardNumber = "1111222233334444",
            cardOwnerName = "이지훈",
            cardExpiredDate = "22 / 33",
            bankType = BankTypeModel.SHINHAN
        )
    }
}
