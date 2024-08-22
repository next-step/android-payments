package nextstep.payments.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.onNodeWithContentDescription
import nextstep.payments.BaseComposeTest
import nextstep.payments.R
import nextstep.payments.ui.screen.paymentcards.CreditCardScreen
import nextstep.payments.ui.screen.paymentcards.CreditCardUiState
import nextstep.payments.ui.screen.paymentcards.model.CreditCard
import org.junit.Before
import org.junit.Test

internal class CreditCardScreenTest : BaseComposeTest() {

    private val state = mutableStateOf(CreditCardUiState(cards = emptyList()))

    private val emptySectionDescription = resourceTestRule.getString(R.string.credit_card_section_zero)
    private val oneItemSectionDescription = resourceTestRule.getString(R.string.credit_card_section_one)
    private val manyItemSectionDescription = resourceTestRule.getString(R.string.credit_card_section_many)
    private val creditCardAddDescription = resourceTestRule.getString(R.string.credit_card_add)
    private val creditCardAddActionText = resourceTestRule.getString(R.string.credit_card_top_bar_action_text)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CreditCardScreen(state = state.value, onAddClick = { /*TODO*/ })
        }
    }

    @Test
    fun 카드가_0개_일때_비어있는_안내_화면이_떠야한다() {
        state.value = CreditCardUiState(cards = emptyList())

        composeTestRule
            .onNodeWithContentDescription(emptySectionDescription)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddDescription)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddActionText)
            .assertDoesNotExist()
    }

    @Test
    fun 카드가_1개_일때_1개의_카드가_보이고_크레딧카드_추가_버튼이_보인다() {
        state.value = CreditCardUiState(
            cards = listOf(
                CreditCard(
                    cardNumber = "1111222233334444",
                    cardOwnerName = "이지훈",
                    cardExpiredDate = "22 / 33"
                )
            )
        )

        composeTestRule
            .onNodeWithContentDescription(oneItemSectionDescription)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddDescription)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddActionText)
            .assertDoesNotExist()
    }

    @Test
    fun 카드가_2개_이상일때_카드들만_보여야한다() {
        state.value = CreditCardUiState(
            cards = List(2) {
                CreditCard(
                    cardNumber = "1111222233334444",
                    cardOwnerName = "이지훈",
                    cardExpiredDate = "22 / 33"
                )
            }
        )

        composeTestRule
            .onNodeWithContentDescription(manyItemSectionDescription)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddDescription)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithContentDescription(creditCardAddActionText)
            .assertDoesNotExist()
    }
}
