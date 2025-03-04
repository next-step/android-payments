package nextstep.payments.card_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.cart_list.CardListScreen
import nextstep.payments.cart_list.CardListUiState
import nextstep.payments.data.dummyDataList
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 등록된_카드가_없을_때_등록_안내_텍스트와_등록_버튼이_노출된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                onAddClick = {},
                cardListUiState = CardListUiState.Empty
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertIsDisplayed()
    }

    @Test
    fun 등록된_카드가_1개일_때_등록안내_문구는_비노출이고_등록_버튼만_노출된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                onAddClick = {},
                cardListUiState = CardListUiState.One(
                    card = dummyDataList.first()
                )
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertIsDisplayed()
    }

    @Test
    fun 등록된_카드가_1개보다_많을_때_등록안내_문구와_등록_버튼이_보이지_않는다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                onAddClick = {},
                cardListUiState = CardListUiState.Many(
                    cards = dummyDataList
                )
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertIsNotDisplayed()
    }
}