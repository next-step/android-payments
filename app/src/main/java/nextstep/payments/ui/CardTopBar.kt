package nextstep.payments.ui

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
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    cardChanged: Boolean = true,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.new_card_top_bar_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSaveClick() },
                enabled = cardChanged,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.new_card_top_bar_done),
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun NewCardTopBarPreview() {
    CardTopBar(
        onBackClick = { },
        onSaveClick = { },
        title = "카드 추가",
    )
}
