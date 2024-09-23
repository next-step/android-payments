package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.label
import nextstep.payments.ui.theme.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppBar(
    isEnabledOfAddButton: Boolean,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.cardlist_top_app_bar_title),
                style = title,
            )
        },
        actions = {
            if (isEnabledOfAddButton) {
                Text(
                    text = stringResource(R.string.cardlist_top_app_bar_button),
                    style = label,
                    modifier = Modifier
                        .clickable { onAddCardClick() }
                        .padding(all = 20.dp),
                )
            }
        },
        modifier = modifier.padding(vertical = 18.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopBarPreview() {
    CardListTopAppBar(
        isEnabledOfAddButton = true,
        onAddCardClick = { },
    )
}
