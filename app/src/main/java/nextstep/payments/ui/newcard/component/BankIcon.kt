package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun BankIcon(
    bank: CardBankInformation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Image(
            painter = painterResource(id = bank.iconRes),
            contentDescription = null,
            modifier = Modifier.size(37.dp)
        )
        Text(
            text = stringResource(id = bank.nameRes),
            style = PaymentsTheme.typography.roboto16M,
            color = Color(0xFF525252)
        )
    }
}

@Preview
@Composable
private fun CompanyIconPreview() {
    PaymentsTheme {
        BankIcon(
            bank = CardBankInformation.Bc,
        )
    }
}
