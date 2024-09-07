package nextstep.payments.ui.newcard.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R.string.newcard_top_app_bar_actions_icon
import nextstep.payments.R.string.newcard_top_app_bar_back_navigation_icon
import nextstep.payments.R.string.newcard_top_app_bar_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(newcard_top_app_bar_title)) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(newcard_top_app_bar_back_navigation_icon),
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(newcard_top_app_bar_actions_icon),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    NewCardTopBar(
        onBackClick = { },
        onSaveClick = { },
    )
}
