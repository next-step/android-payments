package nextstep.payments.cardlist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.component.CreateCardButton
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.TypoTokens.Bold18

@Composable
fun CardListNoCardContent(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "새로운 카드를 등록해주세요",
            style = Bold18
        )
        Spacer(modifier = Modifier.height(32.dp))
        CreateCardButton({})
    }
}

@Preview
@Composable
private fun CardListNoCardContentPreview() {
    PaymentsTheme {
        CardListNoCardContent()
    }
}