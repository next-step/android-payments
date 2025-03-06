package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.components.card.RegisteredPaymentCard
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisteredPaymentCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val card = CardState(
        cardNumber = "1111222233334444",
        expiredDate = "0421",
        ownerName = "CREW",
        password = "0000",
        selectedCardCompany = CardCompanyState.KAKAO,
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RegisteredPaymentCard(card = card, onClick = {})
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
