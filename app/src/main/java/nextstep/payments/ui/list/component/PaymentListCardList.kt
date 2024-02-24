package nextstep.payments.ui.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.domain.model.Card
import nextstep.payments.ui.list.PaymentListUiState

@Composable
internal fun PaymentListCardList(
    uiState: PaymentListUiState,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier,
    ) {
        when (uiState) {
            PaymentListUiState.Empty -> item {
                PaymentListEmpty(onAddCardClick = onAddCardClick)
            }
            is PaymentListUiState.One -> {
                item {
                    PaymentListOne(
                        card = uiState.card,
                        onAddCardClick = onAddCardClick
                    )
                }
            }
            is PaymentListUiState.Many -> Unit
        }
    }
}

@Composable
private fun PaymentListEmpty(onAddCardClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.payment_list_empty_title))
        Spacer(modifier = Modifier.height(32.dp))
        PaymentListAddCard(
            onClick = onAddCardClick,
            modifier = Modifier.size(208.dp, 124.dp)
        )
    }
}

@Composable
private fun PaymentListOne(
    card: Card,
    onAddCardClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        PaymentListCardListItem(
            card = card,
            modifier = Modifier.width(208.dp),
        )
        PaymentListAddCard(
            onClick = onAddCardClick,
            modifier = Modifier.size(208.dp, 124.dp)
        )
    }
}
