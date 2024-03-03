package nextstep.payments.ui.payments

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.payments.R
import org.junit.Rule
import org.junit.Test

class PaymentsCardsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_없을때_카드추가타이틀과_추가카드가_보여야한다() {
        // Given
        val uiState = PaymentCardsUiState.Empty

        // When
        composeTestRule.setContent {
            PaymentCardsScreen(uiState = uiState, onAddCardClick = {})
        }

        // Then
        composeTestRule.onNodeWithText(getString(R.string.payments_add_card_title))
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("AddCard")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1장일때_카드한장과_추가카드가_보여야한다() {
        // Given
        val card = mockPaymentCard()
        val uiState = PaymentCardsUiState.One(card)

        // When
        composeTestRule.setContent {
            PaymentCardsScreen(uiState = uiState, onAddCardClick = {})
        }

        // Then
        composeTestRule.onNodeWithTag("PaymentCard")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(card.ownerName)
            .assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("PaymentCard")
            .assertCountEquals(1)

        composeTestRule.onNodeWithTag("AddCard")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_여러장일때_카드들이_보여아한다() {
        // Given
        val cards = listOf(mockPaymentCard(), mockPaymentCard(), mockPaymentCard())
        val uiState = PaymentCardsUiState.Many(cards)

        // When
        composeTestRule.setContent {
            PaymentCardsScreen(uiState = uiState, onAddCardClick = {})
        }

        // Then
        composeTestRule.onAllNodesWithTag("PaymentCard")
            .assertCountEquals(cards.size)

        composeTestRule.onNodeWithText(getString(R.string.payments_add_card))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("AddCard")
            .assertDoesNotExist()
    }

    private fun getString(@StringRes id: Int): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(id)
    }
}
