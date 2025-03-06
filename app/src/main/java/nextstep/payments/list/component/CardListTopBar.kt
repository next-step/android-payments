package nextstep.payments.list.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    modifier: Modifier = Modifier,
    rightButton: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        actions = rightButton,
        modifier = modifier
    )
}
