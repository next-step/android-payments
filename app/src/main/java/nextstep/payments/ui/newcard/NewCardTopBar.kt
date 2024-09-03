package nextstep.payments.ui.newcard

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
fun NewCardTopBar(
    canEdit: Boolean,
    isCardModified: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            val title = if (canEdit) R.string.edit_card else R.string.add_card
            Text(stringResource(title))
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { if (isCardModified) onSaveClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.complete),
                )
            }
        },
        modifier = modifier
    )
}

@Preview(name = "카드 추가", showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    NewCardTopBar(
        canEdit = false,
        isCardModified = false,
        onBackClick = { },
        onSaveClick = { }
    )
}

@Preview(name = "카드 수정", showBackground = true)
@Composable
private fun EditCardTopBarPreview() {
    NewCardTopBar(
        canEdit = true,
        isCardModified = false,
        onBackClick = { },
        onSaveClick = { }
    )
}


