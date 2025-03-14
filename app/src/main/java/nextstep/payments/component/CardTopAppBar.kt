package nextstep.payments.component

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
fun CardTopAppBar(
    title: String,
    isCompleteButtonEnabled: Boolean,
    onBackClick: () -> Unit,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(title) },
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
                onClick = { onCompleteClick() },
                enabled = isCompleteButtonEnabled,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun EditCardTopAppBarPreview() {
    CardTopAppBar(
        title = "카드 수정",
        isCompleteButtonEnabled = true,
        onBackClick = {},
        onCompleteClick = {},
    )
}

@Preview
@Composable
private fun NewCardTopAppBarPreview() {
    CardTopAppBar(
        title = "카드 추가",
        isCompleteButtonEnabled = false,
        onBackClick = {},
        onCompleteClick = {},
    )
}