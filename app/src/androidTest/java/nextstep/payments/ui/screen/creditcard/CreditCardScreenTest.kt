package nextstep.payments.ui.screen.creditcard

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.BaseComposeTest
import nextstep.payments.R
import nextstep.payments.ui.screen.creditcard.model.CardModel
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import org.junit.Before
import org.junit.Test

internal class CreditCardScreenTest : BaseComposeTest() {

    private val state = mutableStateOf(CreditCardUiState(cards = emptyList()))
    private var onAddClickCount = 0

    private val emptySectionDescription = resourceTestRule.getString(R.string.credit_card_section_zero)
    private val oneItemSectionDescription = resourceTestRule.getString(R.string.credit_card_section_one)
    private val manyItemSectionDescription = resourceTestRule.getString(R.string.credit_card_section_many)
    private val creditCardAddDescription = resourceTestRule.getString(R.string.credit_card_add)
    private val creditCardAddActionText = resourceTestRule.getString(R.string.credit_card_top_bar_action_text)

    private val addCardComponent get() = composeTestRule.onNodeWithContentDescription(creditCardAddDescription)
    private val addCardAction get() = composeTestRule.onNodeWithText(creditCardAddActionText)

    @Before
    fun setUp() {
        onAddClickCount = 0
        composeTestRule.setContent {
            CreditCardScreen(state = state.value, onAddClick = { onAddClickCount += 1 })
        }
    }

    @Test
    fun 카드가_0개일_때_비어있는_안내_화면이_보여야_한다() {
        state.value = CreditCardUiState(cards = emptyList())

        composeTestRule
            .onNodeWithContentDescription(emptySectionDescription)
            .assertIsDisplayed()
        addCardComponent.assertIsDisplayed()
        addCardAction.assertIsNotDisplayed()
    }

    @Test
    fun 카드가_1개일_때_해당_카드와_크레딧카드_추가_버튼이_보여야_한다() {
        state.value = CreditCardUiState(
            cards = listOf(
                CardModel(
                    cardNumber = "1111222233334444",
                    cardOwnerName = "이지훈",
                    cardExpiredDate = "22 / 33",
                    bankType = BankTypeModel.SHINHAN,
                )
            )
        )

        composeTestRule
            .onNodeWithContentDescription(oneItemSectionDescription)
            .assertIsDisplayed()
        addCardAction.assertIsNotDisplayed()
        addCardComponent.assertIsDisplayed()
    }

    @Test
    fun 카드가_2개_이상일_때_카드들만_보여야_하고_추가_버튼이_있어야_한다() {
        state.value = CreditCardUiState(
            cards = List(3) {
                CardModel(
                    cardNumber = "1111222233334444",
                    cardOwnerName = "이지훈",
                    cardExpiredDate = "22 / 33",
                    bankType = BankTypeModel.SHINHAN,
                )
            }
        )

        composeTestRule
            .onNodeWithContentDescription(manyItemSectionDescription)
            .assertIsDisplayed()
        addCardAction.assertIsDisplayed()
        addCardComponent.assertIsNotDisplayed()
    }

    @Test
    fun 카드가2개_이상이면_상단_바에_카드_추가_버튼을_누를수있다() {
        state.value = CreditCardUiState(
            cards = List(3) {
                CardModel(
                    cardNumber = "1111222233334444",
                    cardOwnerName = "이지훈",
                    cardExpiredDate = "22 / 33",
                    bankType = BankTypeModel.SHINHAN,
                )
            }
        )

        addCardAction.performClick()

        assert(onAddClickCount == 1)
    }

    @Test
    fun 카드가2개_이하이면_카드_목록의_추가_버튼_누를수있다() {
        state.value = CreditCardUiState(cards = emptyList())

        addCardComponent.performClick()
        assert(onAddClickCount == 1)
    }
}
