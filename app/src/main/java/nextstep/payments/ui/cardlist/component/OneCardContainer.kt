package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.designsystem.component.PaymentCard
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import java.time.YearMonth


@Composable
fun OneCardContainer(
    item: Card,
    onItemClick: (Card) -> Unit,
    onRouteToNewCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentCard(item = item, onItemClick = onItemClick)
        AddCardButton(onAddClick = onRouteToNewCard)
    }
}

@Preview(showBackground = true)
@Composable
private fun OneCardContainerPreview() {
    val card = Card(
        type = BankType.HANA,
        number = "1234123412341234",
        expiredDate = YearMonth.of(21, 4),
        ownerName = "홍길동",
        password = "1234"
    )

    PaymentsTheme {
        OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
    }
}