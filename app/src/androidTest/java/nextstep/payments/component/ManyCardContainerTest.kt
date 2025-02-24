package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.component.ManyCardContainer
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class ManyCardContainerTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `초기화면에_카드가_두장인_경우_두개의_카드번호가_보여야_한다`() {
        //given
        val cards = listOf(
            Card(
                type = BankType.HANA,
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
            ManyCardContainer(items = cards, onItemClick = {})
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
    fun `초기화면에_카드가_두장인_경우_두개의_카드사가_보여야_한다`() {
        //given
        val cards = listOf(
            Card(
                type = BankType.HANA,
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
            ManyCardContainer(items = cards, onItemClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("하나카드")
            .assertIsDisplayed()

        //then
        composeTestRule
            .onNodeWithText("카카오뱅크")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_카드가_두장인_경우_특정_카드를_클릭시_이벤트가_전달되어야_한다`() {
        //given
        var clickCard: Card? = null
        val cards = listOf(
            Card(
                type = BankType.KAKAO,
                number = "1234123412341234",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "",
                password = ""
            ),
            Card(
                type = BankType.WOORI,
                number = "2345234523452345",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "",
                password = ""
            )
        )
        composeTestRule.setContent {
            ManyCardContainer(items = cards, onItemClick = { clickCard = it })
        }

        composeTestRule
            .onNodeWithText("카카오뱅크")
            .performClick()


        //then
        assertTrue(clickCard == cards[0])
    }
}