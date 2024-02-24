package nextstep.payments.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.list.component.PaymentListCardList
import nextstep.payments.ui.list.component.PaymentListTopAppBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun PaymentListScreen(viewModel: PaymentListViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    PaymentListScreen(
        uiState = uiState,
        onAddClick = {},
        onAddCardClick = {},
    )
}

@Composable
internal fun PaymentListScreen(
    uiState: PaymentListUiState,
    onAddClick: () -> Unit,
    onAddCardClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PaymentListTopAppBar(
            showAdd = uiState is PaymentListUiState.Many,
            onAddClick = onAddClick,
        )
        
        PaymentListCardList(
            uiState = uiState,
            onAddCardClick = onAddCardClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentListScreenPreview() {
    PaymentsTheme {
        val uiState = PaymentListUiState.Empty
        PaymentListScreen(
            uiState = uiState,
            onAddClick = {},
            onAddCardClick = {},
        )
    }
}
