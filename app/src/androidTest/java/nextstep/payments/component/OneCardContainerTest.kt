package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.component.OneCardContainer
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class OneCardContainerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_한개의_카드번호가_보여야_한다`() {
        //given
        val card = Card(
            type = BankType.BC,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("1234 – 1234 – **** – ****")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_한개의_카드_유효기간이_보여야_한다`() {
        //given
        val card = Card(
            type = BankType.BC,
            number = "2345234523452345",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("12 / 28")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_한개의_카드사가_보여야_한다`() {
        //given
        val card = Card(
            type = BankType.BC,
            number = "2345234523452345",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("비씨카드")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_추가버튼이_보여야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "3456345634563456",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .assertIsDisplayed()

    }

    @Test
    fun `초기화면에_추가텍스트가_보이지_않아야_한다`() {


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
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()

    }

    @Test
    fun `추가버튼을_클릭시_카드추가_화면으로_전환하는_이벤트가_전달되어야_한다`() {

        //given
        var isSendOnRouteToNewCardEvent = false
        val card = Card(
            type = BankType.LOTTE,
            number = "1234123412341234",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {}, onRouteToNewCard = {
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
    fun `카드를_클릭시_카드_클릭_이벤트가_전달되어야_한다`() {

        //given
        var sendCard: Card? = null

        val card = Card(
            type = BankType.LOTTE,
            number = "1234123412341234",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )

        composeTestRule.setContent {
            OneCardContainer(item = card, onItemClick = {
                sendCard = it
            }, onRouteToNewCard = {})
        }

        composeTestRule
            .onNodeWithText("롯데카드")
            .performClick()

        //then
        assertTrue(sendCard == card)
    }

}