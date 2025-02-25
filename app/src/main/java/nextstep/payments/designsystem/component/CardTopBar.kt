package nextstep.payments.designsystem.component

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
import nextstep.payments.designsystem.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTopBar(
    title: @Composable () -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CardTopBarPreview() {
    PaymentsTheme {
        CardTopBar(
            title = { Text("타이틀") },
            onBackClick = {},
            onSaveClick = {}
        )
    }

}