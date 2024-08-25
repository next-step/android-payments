package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBarWithAdd(modifier: Modifier = Modifier, onClickAdd: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = "Payments",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = onClickAdd
            ) {
                Text(
                    text = "추가"
                )
            }
        },
        modifier = Modifier
    )
}

@Preview
@Composable
private fun CardListManyTopBarPreview() {
    PaymentsTheme {
        CardListTopBarWithAdd()
    }
}

