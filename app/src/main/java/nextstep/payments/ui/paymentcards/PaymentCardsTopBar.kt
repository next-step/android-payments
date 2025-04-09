package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardsTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    isAddButtonVisible: Boolean = false
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.payment_cards_title)) },
        actions = {
            if (isAddButtonVisible) {
                Text(
                    text = stringResource(R.string.add),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { onAddClick() }
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun ManyPaymentCardsTopBarPreview() {
    PaymentsTheme {
        PaymentCardsTopBar(
            isAddButtonVisible = true,
        )
    }
}

@Preview
@Composable
private fun UnderOnePaymentCardTopBarPreview() {
    PaymentsTheme {
        PaymentCardsTopBar(
            isAddButtonVisible = false
        )
    }
}