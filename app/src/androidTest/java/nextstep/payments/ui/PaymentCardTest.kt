package nextstep.payments.ui

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.payments.component.card.PaymentCard
import nextstep.payments.component.card.PaymentCardDetail
import nextstep.payments.screen.model.CreditCardUiModel
import org.junit.Rule
import org.junit.Test

internal class PaymentCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드번호_16자리_숫자를_입력했을때_뒤8자리는_asterisk로_보인다() {
        //GIVEN
        composeTestRule.setContent {
            PaymentCard {
                PaymentCardDetail(
                    card = CreditCardUiModel(
                        cardNumber = "1234123412341234",
                        firstCardDigits = "1234",
                        secondCardDigits = "1234",
                        ownerName = "",
                        password = "",
                        month = "",
                        year = ""
                    )
                )
            }
        }

        //THEN
        composeTestRule
            .onNodeWithTag("cardNumberText")
            .assertTextContains("1234 - 1234 - **** - ****")
    }

    @Test
    fun 만료일_4자리_숫자를_입력했을때_월과_년이_구분자로_나뉘어_보인다() {
        //GIVEN
        composeTestRule.setContent {
            PaymentCard {
                PaymentCardDetail(
                    card = CreditCardUiModel(
                        cardNumber = "",
                        firstCardDigits = "",
                        secondCardDigits = "",
                        ownerName = "",
                        password = "",
                        month = "04",
                        year = "13"
                    )
                )
            }
        }

        //THEN
        composeTestRule
            .onNodeWithTag("expiredDateText")
            .assertTextContains("04 / 13")
    }

}