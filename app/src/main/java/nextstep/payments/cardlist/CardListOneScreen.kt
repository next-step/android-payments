package nextstep.payments.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.model.CreditCard
import nextstep.payments.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CardListOneScreen(
    card: CreditCard
) {
    Scaffold(
        topBar = { CardListOneTopAppBar() },
        content = { paddingValues ->
            CardListOneContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardListOneTopAppBar() {
    CenterAlignedTopAppBar(title = { Text(text = "Payments") })
}

@Composable
private fun CardListOneContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard()
    }
}

@Preview
@Composable
private fun CardListOneScreenPreview() {
    val card = CreditCard(
        cardNumber = "0000000000000000",
        expiredDate = "1024",
        ownerName = "이범석",
        password = "1234"
    )
    PaymentsTheme {
        CardListOneScreen(card = card)
    }
}