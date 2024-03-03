package nextstep.payments.ui.payments.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.component.AddCard
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.payments.PaymentCardsUiState
import nextstep.payments.ui.payments.PaymentCardsUiStatePreviewParameterProvider

@Composable
fun PaymentCardList(
    uiState: PaymentCardsUiState,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            PaymentCardsUiState.Empty -> {
                item {
                    AddCardTitle(modifier = Modifier.padding(top = 20.dp))
                }
                item {
                    AddCard(onClick = onAddCardClick)
                }
            }

            is PaymentCardsUiState.One -> {
                item {
                    PaymentCard(payment = uiState.card)
                }
                item {
                    AddCard(onClick = onAddCardClick)
                }
            }

            is PaymentCardsUiState.Many -> {
                items(uiState.cards) {
                    PaymentCard(payment = it)
                }
            }
        }
    }
}

@Composable
private fun AddCardTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        text = stringResource(id = R.string.payments_add_card_title),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardListPreview(
    @PreviewParameter(PaymentCardsUiStatePreviewParameterProvider::class) paymentCardsUiState: PaymentCardsUiState
) {
    PaymentCardList(
        uiState = paymentCardsUiState,
        onAddCardClick = {}
    )
}
