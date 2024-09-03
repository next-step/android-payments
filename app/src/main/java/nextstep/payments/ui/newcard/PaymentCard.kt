package nextstep.payments.ui.newcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.Bank
import nextstep.payments.data.model.Card
import nextstep.payments.ui.card.PaymentCardInfo
import nextstep.payments.ui.newcard.model.BankUI
import nextstep.payments.ui.theme.EmptyMainColor

@Composable
fun PaymentCard(
    bankUi: BankUI?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = bankUi?.color ?: EmptyMainColor,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 12.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        content()
    }

}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(null)
}

class PaymentCardDetailProvider : PreviewParameterProvider<Card> {
    private val card = Card(
        cardNumber = "1234123412341234",
        expiredDate = "1234",
        ownerName = "User",
        password = "1234",
        bank = BankUI.BC.toBank()
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
    PaymentCard(BankUI.fromBank(card.bank)) {
        PaymentCardInfo(card = card)
    }
}

