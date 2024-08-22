package nextstep.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.Brand
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var cardNumber by mutableStateOf("")
    private var expiredDate by mutableStateOf("")
    private var ownerName by mutableStateOf("")
    private var brand by mutableStateOf(Brand.NONE)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PaymentCard(
                brand = brand,
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
            )
        }
    }

    @Test
    fun PaymentCard_화면이_정상적으로_렌더링된다() {
        // given
        cardNumber = "1234567890123456"
        expiredDate = "1234"
        ownerName = "홍길동"
        brand = Brand.SHINHAN

        val expectedCardNumber = "1234  -  5678  -  ****  -  ****"
        val expectedExpiredDated = "12 / 34"
        val expectedBrandName = "신한카드"

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
