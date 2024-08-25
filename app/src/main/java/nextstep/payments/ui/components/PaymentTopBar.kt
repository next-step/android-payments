package nextstep.payments.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(
    title: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        }
    )
}

@Preview
@Composable
private fun PaymentTopBarPreview() {
    PaymentTopBar(title = "Payments")

}