package nextstep.payments.ui.newcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Bank
import nextstep.payments.data.model.Card
import nextstep.payments.ui.card.PaymentCardInfo

@Composable
fun PaymentCard(
    bank: Bank? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = bank?.color ?: Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 14.dp, top = 15.dp),
        ) {
            Text(
                text = bank?.bankName.orEmpty(),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 13.dp)
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
        }
    }
}

@Composable
fun PaymentCardDetail(
    card: Card,
    modifier: Modifier = Modifier.testTag("Card"),
    content: @Composable (Bank) -> Unit
) {

    Box(
        modifier = modifier.size(width = 208.dp, height = 124.dp),
    ) {
        content(card.bank)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 16.dp
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            PaymentCardInfo(
                card = card
            )
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard()
}

class PaymentCardDetailProvider: PreviewParameterProvider<Card> {
    private val card = Card(
        cardNumber = "1234123412341234",
        expiredDate = "1234",
        ownerName = "User",
        password = "1234",
        bank = Bank.BC
    )
    override val values: Sequence<Card> = sequenceOf(
        card, card.copy(bank = Bank.SHINHAN), card.copy(bank = Bank.KAKAO),
        card.copy(bank = Bank.HYUNDAI), card.copy(bank = Bank.WOORI), card.copy(bank = Bank.LOTTE),
        card.copy(bank = Bank.HANA), card.copy(bank = Bank.KB)
    )
}

@Preview
@Composable
private fun PaymentCardDetailPreview(
    @PreviewParameter(PaymentCardDetailProvider::class) card: Card
) {
    PaymentCardDetail(card = card) {
        PaymentCard(card.bank)
    }
}

