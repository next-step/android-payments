package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import nextstep.payments.model.BankType
import nextstep.payments.ui.newcard.component.BankSelectItem
import org.junit.Rule
import org.junit.Test

class BankSelectItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `주어진_타입에_맞게_카드사가_보여야_한다`() {

        //given,when
        composeTestRule.setContent {
            BankSelectItem(
                item = BankType.HANA,
                onItemClick = {}
            )
        }

        //then
        composeTestRule
            .onNodeWithText("하나카드")
            .assertIsDisplayed()
    }

    @Test
    fun `주어진_타입에_맞게_카드사_이미지가_보여야_한다`() {

        val bankType = BankType.HANA

        //given,when
        composeTestRule.setContent {
            BankSelectItem(
                item = bankType,
                onItemClick = {}
            )
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("${bankType.image}")
            .assertIsDisplayed()
    }

    @Test
    fun `카드를_클릭시_이벤트가_전달되어야_한다`() {

        val bankType = BankType.HANA

        var sendBankType: BankType? = null

        //given
        composeTestRule.setContent {
            BankSelectItem(
                item = bankType,
                onItemClick = { sendBankType = it }
            )
        }
        //when
        composeTestRule
            .onNodeWithText("하나카드")
            .performClick()


        //then
        assertTrue(sendBankType == bankType)
    }
}