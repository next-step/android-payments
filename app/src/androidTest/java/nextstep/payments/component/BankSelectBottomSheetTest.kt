package nextstep.payments.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.model.BankType
import nextstep.payments.ui.newcard.component.BankSelectBottomSheet
import org.junit.Rule
import org.junit.Test

class BankSelectBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `카드사를_선택하지_않으면_카드사를_선택하는_시트가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            BankSelectBottomSheet(selectedBank = BankType.NOT_SELECTED) { }
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("BankSelectBottomSheet")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하면_시트가_보이지_않아야_한다`() {

        //given
        var selectBank by mutableStateOf(BankType.NOT_SELECTED)
        composeTestRule.setContent {
            BankSelectBottomSheet(selectedBank = selectBank) {
                selectBank = it
            }
        }

        //when
        composeTestRule
            .onNodeWithText("현대카드")
            .performClick()

        //then
        composeTestRule
            .onNodeWithContentDescription("BankSelectBottomSheet")
            .assertIsNotDisplayed()
    }
}