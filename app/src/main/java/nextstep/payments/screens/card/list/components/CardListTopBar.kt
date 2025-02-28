package nextstep.payments.screens.card.list.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardListTopBar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.card_list_title)) },
        actions = {
            TextButton(onClick = onAddClick) {
                Text(
                    text = stringResource(R.string.card_list_button_add),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W700),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        modifier = modifier,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardListTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.card_list_title)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        modifier = modifier,
    )
}

@Preview(name = "추가 버튼이 안보일 때")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardListTopBar()
    }
}

@Preview(name = "추가 버튼 보일 때")
@Composable
private fun Preview2() {
    PaymentsTheme {
        CardListTopBar(
            onAddClick = {},
        )
    }
}
