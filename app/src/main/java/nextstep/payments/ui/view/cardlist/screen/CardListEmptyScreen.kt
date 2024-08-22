package nextstep.payments.ui.view.cardlist.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.component.PaymentCardRegister

@Composable
fun CardListEmptyScreen(
    onRegisterPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.card_list_empty_title),
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        PaymentCardRegister(
            onClick = { onRegisterPaymentCard() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListEmptyScreenPreview() {
    CardListEmptyScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        onRegisterPaymentCard = {}
    )
}
