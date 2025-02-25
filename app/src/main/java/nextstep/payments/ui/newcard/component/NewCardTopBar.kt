package nextstep.payments.ui.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.designsystem.component.CardTopBar
import nextstep.payments.designsystem.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CardTopBar(
        title = { Text(stringResource(R.string.top_app_bar_new_card_title)) },
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    PaymentsTheme {
        NewCardTopBar(
            onBackClick = {},
            onSaveClick = {},
        )
    }
}