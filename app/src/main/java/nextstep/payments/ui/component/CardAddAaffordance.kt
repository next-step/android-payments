package nextstep.payments.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun CardAddAffordance(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.card_add_affordance),
        modifier = modifier
            .fillMaxWidth(),
        style = Typography.titleSmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Preview
@Composable
private fun CardAddAffordancePreview() {
    PaymentsTheme {
        CardAddAffordance()
    }
}