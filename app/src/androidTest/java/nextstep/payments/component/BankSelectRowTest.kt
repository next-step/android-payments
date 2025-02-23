package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.model.BankType
import nextstep.payments.ui.newcard.component.BankSelectRow
import org.junit.Rule
import org.junit.Test

class BankSelectRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `주어진_카드타입_리스트의_카드이름이_모두_보여야_한다`() {

        //given
        val types = listOf(
            BankType.HYUNDAI,
            BankType.KB,
        )
        //when
        composeTestRule.setContent {
            BankSelectRow(
                items = types,
                onItemClick = {}
            )
        }

        //then
        composeTestRule
            .onNodeWithText("현대카드")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("국민카드")
            .assertIsDisplayed()
    }

    @Test
    fun `주어진_카드타입_리스트의_카드이미지가_모두_보여야_한다`() {

        //given
        val types = listOf(
            BankType.HANA,
            BankType.WOORI,
        )
        //when
        composeTestRule.setContent {
            BankSelectRow(
                items = types,
                onItemClick = {}
            )
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("${BankType.HANA.image}")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("${BankType.WOORI.image}")
            .assertIsDisplayed()
    }

    @Test
    fun `주어진_카드타입_리스트중_한개를_클릭시_이벤트가_잘_전달되어야_한다`() {

        //given
        var selectType: BankType? = null
        composeTestRule.setContent {
            BankSelectRow(
                items = BankType.getSelectBanks(),
                onItemClick = {
                    selectType = it
                }
            )
        }

        //when
        composeTestRule
            .onNodeWithText("현대카드")
            .performClick()


        //then
        assert(selectType == BankType.HYUNDAI)
    }

}