package nextstep.payments.ui.payments

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsTopBar(
    isAddable: Boolean,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.payments_top_bar_title), fontSize = 22.sp) },
        actions = {
            if (isAddable) {
                TextButton(onClick = { onAddClick() }) {
                    Text(
                        text = stringResource(R.string.payments_top_bar_action_add),
                        fontWeight = W700,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }
        },
        modifier = modifier
    )
}

class PaymentsTopBarPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
private fun PaymentsTopBarPreview(
    @PreviewParameter(PaymentsTopBarPreviewParameterProvider::class) isAddable: Boolean
) {
    PaymentsTheme {
        PaymentsTopBar(onAddClick = {}, isAddable = isAddable)
    }
}
