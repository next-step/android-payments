package nextstep.payments.ui.cards.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardTopBar(
    isShowAddButton: Boolean,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Payments")
        },
        actions = {
            if (isShowAddButton) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = "추가",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun ShowActionBarCreditCardTopBarPreview() {
    CreditCardTopBar(isShowAddButton = true) {}
}

@Preview
@Composable
private fun HideActionBarCreditCardTopBarPreview() {
    CreditCardTopBar(isShowAddButton = false) {}
}