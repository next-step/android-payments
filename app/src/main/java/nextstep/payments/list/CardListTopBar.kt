package nextstep.payments.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    showAddButton: Boolean = false,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier.testTag("CardListTopBar"),
        title = { Text(stringResource(R.string.payments)) },
        actions = {
            if (showAddButton) {
                Text(
                    modifier = Modifier
                        .clickable(onClick = onAddClick)
                        .padding(8.dp)
                        .testTag("Add")
                    ,
                    text = stringResource(R.string.add)
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardListTopBarPreview() {
    PaymentsTheme {
        CardListTopBar(onAddClick = {}, showAddButton = true)
    }
}
