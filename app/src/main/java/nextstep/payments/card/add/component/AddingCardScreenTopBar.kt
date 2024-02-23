package nextstep.payments.card.add.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingCardScreenTopBar(
    onBackButtonClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.adding_card_screen_top_bar_title)) },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveButtonClick) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                )
            }
        }
    )
}

@Preview
@Composable
private fun AddingCardScreenTopBarPreview() {
    AddingCardScreenTopBar(
        onBackButtonClick = {},
        onSaveButtonClick = {},
    )
}
