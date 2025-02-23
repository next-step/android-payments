package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun ManyCardContainer(
    items: List<Card>,
    onItemClick: (Card) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        items(items) { item ->
            PaymentCard(item = item, onItemClick = onItemClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ManyCardContainerPreview() {
    val cards = listOf(
        Card(
            type = BankType.HANA,
            number = "1234123412341234",
            expiredDate = YearMonth.of(21, 4),
            ownerName = "홍길동",
            password = "1234"
        ),
        Card(
            type = BankType.KAKAO,
            number = "2345234523452345",
            expiredDate = YearMonth.of(22, 4),
            ownerName = "홍길동",
            password = "1234"
        ),
        Card(
            type = BankType.KB,
            number = "3456345634563456",
            expiredDate = YearMonth.of(23, 4),
            ownerName = "홍길동",
            password = "1234"
        ),
        Card(
            type = BankType.HYUNDAI,
            number = "4567456745674567",
            expiredDate = YearMonth.of(24, 4),
            ownerName = "홍길동",
            password = "1234"
        )
    )
    PaymentsTheme {
        ManyCardContainer(items = cards, onItemClick = {})
    }
}