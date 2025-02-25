package nextstep.payments.ui.updatecard.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.designsystem.component.CardTopBar

@Composable
fun UpdateCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CardTopBar(
        title = { Text(stringResource(R.string.top_app_bar_update_card_title)) },
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun UpdateCardTopBarPreview() {
    UpdateCardTopBar(
        onSaveClick = {},
        onBackClick = {}
    )
}