package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.BankType

private const val DEFAULT_BACKGROUND_COLOR = 0xFF333333

@Composable
fun NewPaymentCard(
    modifier: Modifier = Modifier,
    bankType: BankType?,
    cardNumber: String,
    ownerName: String,
    expiredDate: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color(bankType?.backgroundColor ?: DEFAULT_BACKGROUND_COLOR))
            .clickable { onClick() }
            .padding(14.dp),
    ) {
        bankType?.let {
            Text(
                text = stringResource(id = it.titleRes),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        }

        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart),
        )

        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Text(
                text = cardNumber,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 14.sp,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = ownerName,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                )
                Text(
                    text = expiredDate,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewPaymentCardPreview(@PreviewParameter(NewPaymentCardPreviewParameterProvider::class) param: NewPaymentCardPreviewParam) {
    NewPaymentCard(
        bankType = param.bankType,
        cardNumber = param.cardNumber,
        ownerName = param.ownerName,
        expiredDate = param.expiredDate,
        onClick = {},
    )
}

private class NewPaymentCardPreviewParameterProvider :
    PreviewParameterProvider<NewPaymentCardPreviewParam> {
    override val values: Sequence<NewPaymentCardPreviewParam> = sequenceOf(
        NewPaymentCardPreviewParam(null, "", "", ""),
        NewPaymentCardPreviewParam(BankType.BC, "1111 - 2222 - 3333 - 4444", "jay kang", "08/27"),
    )
}

private data class NewPaymentCardPreviewParam(
    val bankType: BankType?,
    val cardNumber: String,
    val ownerName: String,
    val expiredDate: String,
)
