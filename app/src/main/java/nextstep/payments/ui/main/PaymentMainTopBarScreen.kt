package nextstep.payments.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.titleBoldStyle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun PaymentMainTopBar(
    size: Int,
    onAddClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Payments",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            if (size > 1) {
                IconButton(
                    onClick = onAddClick,
                    modifier = Modifier.size(64.dp)
                ) {
                    Text(
                        text = "추가",
                        style = titleBoldStyle
                    )
                }
            } else {
                Box(modifier = Modifier.size(64.dp))
            }
        },
        navigationIcon = {
            Box(modifier = Modifier.size(64.dp))
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun PaymentMainTopBarPreview() {
    PaymentMainTopBar(size = 2) {
    }
}