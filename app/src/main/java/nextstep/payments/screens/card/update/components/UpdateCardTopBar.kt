package nextstep.payments.screens.card.update.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCardTopBar(
    title: String,
    saveEnabled: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.all_back),
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveClick, enabled = saveEnabled) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.all_done),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        modifier = modifier,
    )
}

@Preview(name = "저장 버튼 활성화")
@Composable
private fun Preview1() {
    PaymentsTheme {
        UpdateCardTopBar(
            title = "카드 추가",
            saveEnabled = true,
            onBackClick = {},
            onSaveClick = {},
        )
    }
}

@Preview(name = "저장 버튼 비활성화")
@Composable
private fun Preview2() {
    PaymentsTheme {
        UpdateCardTopBar(
            title = "카드 수정",
            saveEnabled = false,
            onBackClick = {},
            onSaveClick = {},
        )
    }
}
