@file:OptIn(ExperimentalMaterial3Api::class)

package nextstep.payments.ui.add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import nextstep.payments.data.model.BankType
import org.junit.Rule
import org.junit.Test

class BankSelectBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `8개의_카드사_목록이_보인다`() {
        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelect = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("카드사 목록")
            .onChildren()
            .assertCountEquals(8)
    }

    @Test
    fun `카드사_선택이_가능하다`() {
        var selectedBank = BankType.NOT_SELECTED

        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelect = { selectedBank = it },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("BC카드")
            .performClick()

        composeTestRule
            .waitForIdle()

        assert(selectedBank == BankType.BC)
    }
}
