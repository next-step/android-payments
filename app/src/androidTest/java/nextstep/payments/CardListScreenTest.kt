package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.CardListScreen
import nextstep.payments.ui.cardlist.CardListUiState
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드리스트가_비어있는경우_새로운_카드를_등록해주세요_텍스트가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Empty, onRouteToNewCard = {})
        }
        //then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `카드리스트가_비어있는경우_추가버튼이_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Empty, onRouteToNewCard = {})
        }
        //then
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .assertIsDisplayed()
    }

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
    fun `카드리스트가_비어있는경우_추가버튼을_클릭시_카드추가_화면으로_전환하는_이벤트가_전달되어야_한다`() {
        //given
        var isSendOnRouteToNewCardEvent = false
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Empty, onRouteToNewCard = {
                isSendOnRouteToNewCardEvent = true
            })
        }

        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .performClick()


        //then
        assertTrue(isSendOnRouteToNewCardEvent)
    }


    @Test
    fun `카드리스트가_하나만_있는경우_한개의_카드번호가_보여야_한다`() {

        //given
        val card = Card(
            number = "1234123412341234",
            expiredDate = "1227",
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.One(card), onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("1234 – 1234 – **** – ****")
            .assertIsDisplayed()
    }

    @Test
    fun `카드리스트가_하나만_있는경우_한개의_카드_유효기간이_보여야_한다`() {

        //given
        val card = Card(
            number = "2345234523452345",
            expiredDate = "1228",
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.One(card), onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("12 / 28")
            .assertIsDisplayed()
    }

    @Test
    fun `카드리스트가_하나만_있는경우_추가버튼이_보여야_한다`() {

        //given
        val card = Card(
            number = "3456345634563456",
            expiredDate = "1228",
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.One(card), onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .assertIsDisplayed()
    }

    @Test
    fun `카드리스트가_하나만_있는경우_추가텍스트가_보이지_않아야_한다`() {

        //given
        val card = Card(
            number = "1234123412341234",
            expiredDate = "1228",
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
    fun `카드리스트가_하나만_있는경우_추가버튼을_클릭시_카드추가_화면으로_전환하는_이벤트가_전달되어야_한다`() {
        //given
        var isSendOnRouteToNewCardEvent = false
        val card = Card(
            number = "1234123412341234",
            expiredDate = "1228",
            ownerName = "",
            password = ""
        )
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.One(card), onRouteToNewCard = {
                isSendOnRouteToNewCardEvent = true
            })
        }

        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .performClick()


        //then
        assertTrue(isSendOnRouteToNewCardEvent)
    }

    @Test
    fun `카드리스트가_여러개인_경우_두개의_카드번호가_보여야_한다`() {
        //given
        val cards = listOf(
            Card(
                number = "1234123412341234",
                expiredDate = "1228",
                ownerName = "",
                password = ""
            ),
            Card(
                number = "2345234523452345",
                expiredDate = "1228",
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
            .onNodeWithText("1234 – 1234 – **** – ****")
            .assertIsDisplayed()

        //then
        composeTestRule
            .onNodeWithText("2345 – 2345 – **** – ****")
            .assertIsDisplayed()
    }

    @Test
    fun `카드리스트가_여러개인_경우_추가_텍스트가_보여야_한다`() {
        //given
        val cards = listOf(
            Card(
                number = "1234123412341234",
                expiredDate = "1228",
                ownerName = "",
                password = ""
            ),
            Card(
                number = "2345234523452345",
                expiredDate = "1228",
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

    @Test
    fun `카드리스트가_여러개인_경우_추가_텍스트를_클릭시_카드추가_화면으로_전환하는_이벤트가_전달되어야_한다`() {
        //given
        var isSendOnRouteToNewCardEvent = false
        val cards = listOf(
            Card(
                number = "1234123412341234",
                expiredDate = "1228",
                ownerName = "",
                password = ""
            ),
            Card(
                number = "2345234523452345",
                expiredDate = "1228",
                ownerName = "",
                password = ""
            )
        )
        composeTestRule.setContent {
            CardListScreen(uiState = CardListUiState.Many(cards), onRouteToNewCard = {
                isSendOnRouteToNewCardEvent = true
            })
        }

        composeTestRule
            .onNodeWithText("추가")
            .performClick()


        //then
        assertTrue(isSendOnRouteToNewCardEvent)
    }
}