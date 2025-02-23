package nextstep.payments.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.payments.model.CreditCard
import org.junit.Rule
import org.junit.Test

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPaymentCard() {
        composeTestRule.setContent {
            PaymentCard(
                creditCard = CreditCard(
                    cardNumber = "1234",
                    expiredDate = "12/34",
                    ownerName = "John Doe",
                    password = "1234"
                )
            )
        }
    }
}