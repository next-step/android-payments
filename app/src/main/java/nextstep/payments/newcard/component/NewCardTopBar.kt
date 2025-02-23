package nextstep.payments.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String = "",
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String = "",
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = { onSaveClick() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionIconContentDescription,
                    )
                }
            }

        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    PaymentsTheme {
        NewCardTopBar(onBackClick = {}, onSaveClick = {}, title = "카드 추가")
    }
}
