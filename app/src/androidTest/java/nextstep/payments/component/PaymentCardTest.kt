package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.designsystem.component.PaymentCard
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class PaymentCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드정보가_없는_경우_카드번호가_보이지_않아야_한다`() {

        //given, when
        composeTestRule.setContent {
            PaymentCard()
        }
        //then
        composeTestRule
            .onNodeWithContentDescription("payment_card_number")
            .assertIsNotDisplayed()
    }

    @Test
    fun `카드정보가_없는_경우_카드의_유효기간이_보이지_않아야_한다`() {

        //given, when
        composeTestRule.setContent {
            PaymentCard()
        }
        //then
        composeTestRule
            .onNodeWithContentDescription("payment_card_expired_date")
            .assertIsNotDisplayed()
    }

    @Test
    fun `카드타입이_있는경우_카드사가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            PaymentCard(
                type = BankType.HANA
            )
        }

        //then
        composeTestRule
            .onNodeWithText("하나은행")
            .assertIsNotDisplayed()
    }


    @Test
    fun `카드정보가_있는경우_카드번호가_올바르게_보여야_한다`() {

        //given
        val card = Card(
            type = BankType.BC,
            number = "1234123412341234",
            expiredDate = YearMonth.of(25, 12),
            ownerName = "",
            password = ""
        )
        //when
        composeTestRule.setContent {
            PaymentCard(item = card, onItemClick = {})
        }


        //then
        composeTestRule
            .onNodeWithText("1234 – 1234 – **** – ****")
            .assertIsDisplayed()

    }

    @Test
    fun `카드정보가_있는경우_카드의_유효기간이_올바르게_보여야_한다`() {

        //given
        val card = Card(
            type = BankType.KB,
            number = "2345234523452345",
            expiredDate = YearMonth.of(26, 12),
            ownerName = "",
            password = ""
        )

        //when
        composeTestRule.setContent {
            PaymentCard(item = card, onItemClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("12 / 26")
            .assertIsDisplayed()

    }

    @Test
    fun `카드정보가_있는경우_카드사가_올바르게_보여야_한다`() {

        //given
        val card = Card(
            type = BankType.WOORI,
            number = "2345234523452345",
            expiredDate = YearMonth.of(27, 12),
            ownerName = "",
            password = ""
        )

        //when
        composeTestRule.setContent {
            PaymentCard(item = card, onItemClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("우리카드")
            .assertIsDisplayed()


    }


    @Test
    fun `카드정보가_있는경우_카드를_클릭시_카드정보를_전달하는_이벤트가_발생해야_한다`() {


        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "3456345634563456",
            expiredDate = YearMonth.of(27, 12),
            ownerName = "",
            password = ""
        )
        var sendCardEventItem: Card? = null

        composeTestRule.setContent {
            PaymentCard(item = card, onItemClick = { sendCardEventItem = it })
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("payment_card")
            .performClick()

        //then
        assertEquals(sendCardEventItem, card)
    }
}