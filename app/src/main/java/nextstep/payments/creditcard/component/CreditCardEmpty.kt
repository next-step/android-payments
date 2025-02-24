package nextstep.payments.creditcard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.common.component.NewCard
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardEmpty(onNavigateToNewCard: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = { Text("Payments", fontSize = 22.sp) })
        CreditCardEmptyGuideMessage(
            guideMessage = "새로운 카드를 등록해주세요",
            modifier = modifier.padding(vertical = 32.dp)
        )
        NewCard(onClick = { onNavigateToNewCard() })
    }
}

@Composable
private fun CreditCardEmptyGuideMessage(
    guideMessage: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(guideMessage, fontSize = fontSize, fontWeight = fontWeight, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun CardEmptyPreview() {
    PaymentsTheme {
        CreditCardEmpty({})
    }
}
