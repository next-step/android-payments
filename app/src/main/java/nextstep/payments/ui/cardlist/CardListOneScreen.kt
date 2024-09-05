package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.card.CardNumber
import nextstep.payments.ui.component.card.AddableCard
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.YearMonth

@Composable
internal fun CardListOneScreen(
    card: CardInformation,
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        topBar = { CardListTopAppBar() },
        content = { paddingValues ->
            CardListOneContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                card = card,
                onAddCardClick = onAddCardClick,
            )
        }
    )
}

@Composable
private fun CardListOneContent(
    card: CardInformation,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard(
            cardInformation = card,
        )

        Spacer(modifier = Modifier.height(36.dp))

        AddableCard(onClick = onAddCardClick)
    }
}

@Preview
@Composable
private fun CardListOneScreenPreview() {
    val card = CardInformation(
        numberFirst = CardNumber("1111"),
        numberSecond = CardNumber("1111"),
        expirationDate = YearMonth.now(),
        ownerName = "이범석",
        bank = CardBankInformation.None,
    )
    PaymentsTheme {
        CardListOneScreen(
            card = card,
            onAddCardClick = {},
        )
    }
}