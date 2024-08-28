package nextstep.payments.ui.view.newcard

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
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    isEdit: Boolean,
    canSave: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(if (isEdit) "카드 수정" else "카드 추가") },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSaveClick() },
                enabled = canSave
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier
    )
}

@Preview(name = "카드 추가", showBackground = true)
@Composable
private fun NewCardTopBarPreview1() {
    NewCardTopBar(
        isEdit = false,
        canSave = true,
        onBackClick = {},
        onSaveClick = {}
    )
}

@Preview(name = "카드 수정, 저장 가능", showBackground = true)
@Composable
private fun NewCardTopBarPreview2() {
    NewCardTopBar(
        isEdit = true,
        canSave = true,
        onBackClick = {},
        onSaveClick = {}
    )
}

@Preview(name = "카드 수정, 저장 불가능", showBackground = true)
@Composable
private fun NewCardTopBarPreview3() {
    NewCardTopBar(
        isEdit = true,
        canSave = false,
        onBackClick = {},
        onSaveClick = {}
    )
}
