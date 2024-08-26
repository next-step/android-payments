package nextstep.payments.ui.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import nextstep.payments.BaseComposeTest
import nextstep.payments.R
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.utils.chunkedCardNumber
import nextstep.payments.ui.utils.toFormattedExpirationDate
import nextstep.payments.utils.assertBackgroundColor
import org.junit.Test

class PaymentCardTest : BaseComposeTest() {

    @Test
    fun 카드사에따라_배경색이_적용이된다() {
        val cardOwnerName = "이지훈"
        val cardNumber = "1111222233334444"
        val cardExpiredDate = "2233"
        val shinhanBank = BankTypeModel.SHINHAN
        val kakaoBank = BankTypeModel.KAKAO
        val context = ApplicationProvider.getApplicationContext<Context>()

        val shinhanCardDescription = context.getString(
            R.string.payment_card_content_description,
            shinhanBank.companyName,
            cardOwnerName,
            cardNumber.chunkedCardNumber(),
            cardExpiredDate.toFormattedExpirationDate(
                maxLength = 4,
                separator = "/"
            )
        )

        val kakaoCardDescription = context.getString(
            R.string.payment_card_content_description,
            kakaoBank.companyName,
            cardOwnerName,
            cardNumber.chunkedCardNumber(),
            cardExpiredDate.toFormattedExpirationDate(
                maxLength = 4,
                separator = "/"
            )
        )

        composeTestRule.setContent {
            Column {
                PaymentCard(
                    cardNumber = cardNumber,
                    cardOwnerName = cardOwnerName,
                    cardExpiredDate = cardExpiredDate,
                    bankType = shinhanBank
                )
                PaymentCard(
                    cardNumber = cardNumber,
                    cardOwnerName = cardOwnerName,
                    cardExpiredDate = cardExpiredDate,
                    bankType = kakaoBank
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(shinhanCardDescription)
            .assertBackgroundColor(shinhanBank.color)
        composeTestRule
            .onNodeWithContentDescription(kakaoCardDescription)
            .assertBackgroundColor(kakaoBank.color)
    }
}
