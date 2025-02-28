package nextstep.payments.cardlist.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import nextstep.payments.cardlist.CardListEvent
import nextstep.payments.parameters.BooleanPreviewParameter
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.TypoTokens.Bold18

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    isShowAddButton: Boolean,
    sendEvent: (CardListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("Payments") },
        actions = {
            if (isShowAddButton) {
                Text(
                    text = "추가",
                    style = Bold18,
                    modifier = Modifier.clickable { sendEvent(CardListEvent.OnClickCreateCardButton) }
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun CardListTopBarPreview(
    @PreviewParameter(BooleanPreviewParameter::class) isShowAddButton: Boolean,
) {
    PaymentsTheme {
        CardListTopBar(
            isShowAddButton = isShowAddButton,
            sendEvent = {},
        )
    }
}
