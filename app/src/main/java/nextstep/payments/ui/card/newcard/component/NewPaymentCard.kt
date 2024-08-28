package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(bankType?.backgroundColor ?: DEFAULT_BACKGROUND_COLOR),
                shape = RoundedCornerShape(5.dp),
            )
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
    }
}

@Preview
@Composable
private fun NewPaymentCardPreview(@PreviewParameter(NewPaymentCardPreviewParameterProvider::class) param: BankType?) {
    NewPaymentCard(bankType = param)
}

private class NewPaymentCardPreviewParameterProvider : PreviewParameterProvider<BankType?> {
    override val values: Sequence<BankType?> = sequenceOf(
        null,
        BankType.BC,
    )
}
