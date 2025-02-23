package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.CardListScreen
import nextstep.payments.ui.cardlist.CardListUiState
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드리스트가_비어있는경우_추가_텍스트가_보이지_않아야_한다`() {
        //given, when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Empty, onRouteToNewCard = {})
        }
        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()
    }


    @Test
    fun `카드리스트가_하나만_있는경우_추가텍스트가_보이지_않아야_한다`() {

        //given
        val card = Card(
            type = BankType.KB,
            number = "1234123412341234",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.One(card), onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()
    }


    @Test
    fun `카드리스트가_여러개인_경우_추가_텍스트가_보여야_한다`() {
        //given
        val cards = listOf(
            Card(
                type = BankType.KB,
                number = "1234123412341234",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "",
                password = ""
            ),
            Card(
                type = BankType.KAKAO,
                number = "2345234523452345",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "",
                password = ""
            )
        )

        //when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Many(cards), onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }

}