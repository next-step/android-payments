package nextstep.payments.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
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

@Composable
private fun PaymentListTopAppBar(
    onAddClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.payment_list_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )
        TextButton(
            onClick = onAddClick,
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            Text(
                text = stringResource(R.string.payment_list_add),
                color = Color.Black,
            )
        }
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
