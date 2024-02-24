package nextstep.payments.ui.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class PaymentListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_한개도_없다면_새로운_카드를_등록해주세요_가_보여야_한다() {
        // given
        val uiState = PaymentListUiState.Empty

        // when
        composeTestRule.setContent {
            PaymentListScreen(
                uiState = uiState,
                onAddClick = {},
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }
}
