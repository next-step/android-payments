package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.components.card.RegisteredPaymentCard
import nextstep.payments.domain.Card
import nextstep.payments.domain.CardCompany
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisteredPaymentCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val card = Card(
        numbers = "1111222233334444",
        expiredDate = "0421",
        ownerName = "CREW",
        password = "0000",
        cardCompany = CardCompany(id = 1, name = "KAKAO"),
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RegisteredPaymentCard(card = card)
        }
    }

    @Test
    fun 카드번호는_형식에_맞게_변형되어_표시된다() {
        // then
        composeTestRule.onNodeWithText("1111222233334444").assertDoesNotExist()
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }

    @Test
    fun 카드번호의_뒤_8자리_숫자는_마스킹된다() {
        // then
        composeTestRule.onNodeWithText("1111 - 2222 - 3333 - 4444").assertDoesNotExist()
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }

    @Test
    fun 만료일은_형식에_맞게_변형되어_표시된다() {
        // then
        composeTestRule.onNodeWithText("0421").assertDoesNotExist()
        composeTestRule.onNodeWithText("04 / 21").assertExists()
    }
}
