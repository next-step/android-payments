package nextstep.payments.creditcard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.common.component.NewCard
import nextstep.payments.model.CreditCard
import nextstep.payments.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardOne(
    onNavigateToNewCard: () -> Unit,
    card: CreditCard,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = { Text("Payments", fontSize = 22.sp) })
        PaymentCard(modifier = Modifier.padding(vertical = 12.dp), creditCard = card)
        NewCard(modifier = Modifier.padding(vertical = 24.dp), onClick = { onNavigateToNewCard() })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CreditCardOnePreview() {
    PaymentsTheme {
        CreditCardOne({}, CreditCard("1234567812345678", "0421", "김무현", "1234"))
    }
}
