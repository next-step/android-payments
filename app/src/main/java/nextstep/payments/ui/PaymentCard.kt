package nextstep.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun PaymentCard(
    selectedBankType: BankType?,
    onClickPaymentCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = selectedBankType?.color ?: Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { onClickPaymentCard() }
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
    }
}

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewParameterProvider::class) value: BankType
) {
    PaymentsTheme {
        PaymentCard(value, {})
    }
}

private class PaymentCardPreviewParameterProvider : CollectionPreviewParameterProvider<BankType>(
    BankType.entries
)