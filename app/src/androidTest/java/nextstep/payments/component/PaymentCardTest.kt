package nextstep.payments.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.data.Card
import org.junit.Rule
import org.junit.Test

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드번호의_뒷부분_8글자는_마스킹_처리해서_보여진다() {
        // given
        composeTestRule.setContent {
            PaymentListCard(
                card = Card(
                    cardNumber = "1234567890123456",
                    expiredDate = "1201",
                    ownerName = "홍길동",
                    password = "0000"
                )
            )
        }

        // then
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertExists()
    }

    @Test
    fun 카드의_만료일이_슬래시로_구분하여_보여진다() {
        // given
        composeTestRule.setContent {
            PaymentListCard(
                card = Card(
                    cardNumber = "1234567890123456",
                    expiredDate = "1201",
                    ownerName = "홍길동",
                    password = "0000"
                )
            )
        }

        // then
        composeTestRule
            .onNodeWithText("12 / 01")
            .assertExists()
    }
}