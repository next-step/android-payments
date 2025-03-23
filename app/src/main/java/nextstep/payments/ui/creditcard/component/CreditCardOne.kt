package nextstep.payments.ui.creditcard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.model.CreditCardType.RegisteredCard
import nextstep.payments.ui.component.AddingNewCard
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CreditCardOne(
    onNavigateToNewCard: () -> Unit,
    card: RegisteredCard,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        PaymentCard(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 76.dp),
            creditCardType = card,
        )
        AddingNewCard(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 76.dp),
            onClick = { onNavigateToNewCard() })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CreditCardOnePreview() {
    PaymentsTheme {
        CreditCardOne(
            {}, RegisteredCard(
                "1234567812345678", "0421", "김무현", "1234",
                CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xF444444)
            )
        )
    }
}
