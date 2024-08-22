package nextstep.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.Brand
import org.junit.Rule
import org.junit.Test

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun PaymentCard_화면이_정상적으로_렌더링된다() {
        // given
        val cardNumber = "1234567890123456"
        val expiredDate = "1234"
        val ownerName = "홍길동"
        val brand = Brand.SHINHAN

        val expectedCardNumber = "1234  -  5678  -  ****  -  ****"
        val expectedExpiredDated = "12 / 34"
        val expectedBrandName = "신한카드"

        composeTestRule.setContent {
            PaymentCard(
                brand = brand,
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
            )
        }

        // when & then
        composeTestRule
            .onNodeWithText(expectedBrandName)
            .assertExists()

        composeTestRule
            .onNodeWithText(expectedCardNumber)
            .assertExists()

        composeTestRule
            .onNodeWithText(expectedExpiredDated)
            .assertExists()

        composeTestRule
            .onNodeWithTag("ownerName")
            .assertExists()
    }
}
