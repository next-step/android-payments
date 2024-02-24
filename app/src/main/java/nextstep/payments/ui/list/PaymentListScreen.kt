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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.ui.domain.model.Card
import nextstep.payments.ui.list.component.PaymentListCardList
import nextstep.payments.ui.list.component.PaymentListTopAppBar
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.LocalDate

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

private class PaymentListUiStatePreviewProvider : PreviewParameterProvider<PaymentListUiState> {
    override val values: Sequence<PaymentListUiState> = sequenceOf(
        PaymentListUiState.Empty,
        PaymentListUiState.One(
            card = Card(
                id = "1",
                cardNumber = "1111222233334444",
                ownerName = "CREW",
                expiredDate = LocalDate.of(2021, 4, 1),
                imageUrl = "",
            )
        ),
        PaymentListUiState.Many(
            cards = List(5) {
                Card(
                    id = it.toString(),
                    cardNumber = "1111222233334444",
                    ownerName = "CREW",
                    expiredDate = LocalDate.of(2021, 4, it + 1),
                    imageUrl = "",
                )
            }
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentListScreenPreview(
    @PreviewParameter(PaymentListUiStatePreviewProvider::class) uiState: PaymentListUiState,
) {
    PaymentsTheme {
        PaymentListScreen(
            uiState = uiState,
            onAddClick = {},
            onAddCardClick = {},
        )
    }
}
