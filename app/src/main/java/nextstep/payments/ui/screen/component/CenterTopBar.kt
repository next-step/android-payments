package nextstep.payments.ui.screen.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopBar(
    composeActionButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_app_title),
                fontWeight = FontWeight.W400,
                fontSize = 22.sp
            )
        },
        modifier = modifier,
        actions = {
            composeActionButton()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CenterTopBarPreview() {
    CenterTopBar(
        composeActionButton = {}
    )
}
