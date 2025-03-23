package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Column
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
import nextstep.payments.ui.newcard.AddCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EmptyCardScreen(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 32.dp),
            text = stringResource(R.string.add_new_card_label),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        AddCard(modifier = Modifier.padding(bottom = 32.dp), onClick = onAddClick)
    }
}

@Preview
@Composable
private fun EmptyCardScreenPreview() {
    PaymentsTheme {
        EmptyCardScreen()
    }
}