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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.BankType
import nextstep.payments.data.model.Card
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.util.toCardExpiredDateTransformedText
import nextstep.payments.ui.util.toCardNumberTransformedText

@Composable
internal fun EmptyPaymentCard(
    bankType: BankType,
    modifier: Modifier = Modifier,
) {
    PaymentCardFrame(
        bankType = bankType,
        modifier = modifier.semantics {
            contentDescription = "미완성 카드"
        })
}

@Composable
internal fun PaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    PaymentCardFrame(
        bankType = card.bankType,
        modifier = modifier.semantics {
            contentDescription = "완성 카드"
        }
    ) {
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
                text = card.number.toCardNumberTransformedText().text,
                style = textStyle,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
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
                    text = card.expiredDate.toCardExpiredDateTransformedText().text,
                    style = textStyle,
                )
            }
        }
    }
}

@Composable
private fun PaymentCardFrame(
    bankType: BankType,
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = colorResource(bankType.colorResId))
    ) {
        if (bankType != BankType.NOT_SELECTED) {
            Text(
                text = stringResource(bankType.nameResId),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(14.dp)
            )
        }
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
private fun EmptyPaymentCardPreview() {
    PaymentsTheme {
        EmptyPaymentCard(BankType.NOT_SELECTED)
    }
}

@Preview
@Composable
private fun PaymentCardPreview_card(
    @PreviewParameter(CardPreviewParameter::class) card: Card
) {
    PaymentsTheme {
        PaymentCard(card = card)
    }
}

private class CardPreviewParameter : CollectionPreviewParameterProvider<Card>(
    BankType.getSelectableTypes().mapIndexed { i, bank ->
        Card(
            id = i,
            number = "0000000000000000",
            expiredDate = "0000",
            ownerName = "홍길동",
            password = "0000",
            bankType = bank
        )
    }
)
