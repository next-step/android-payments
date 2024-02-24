package nextstep.payments.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.list.component.PaymentListTopAppBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun PaymentListScreen(viewModel: PaymentListViewModel) {

}

@Composable
internal fun PaymentListScreen(
    onAddClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PaymentListTopAppBar(onAddClick = onAddClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentListScreenPreview() {
    PaymentsTheme {
        PaymentListScreen(
            onAddClick = {},
        )
    }
}
