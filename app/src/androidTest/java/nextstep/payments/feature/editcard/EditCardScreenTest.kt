package nextstep.payments.feature.editcard

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.payments.ui.theme.PaymentsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PaymentsTheme {
                EditCardScreen(
                    onBackClick = {},
                    onSaveClick = {}
                )
            }
        }
    }

    @Test
    fun 카드_정보가_수정되지_않으면_수정버튼을_클릭할_수_없다() {
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsNotEnabled()
    }

    @Test
    fun 카드_번호가_수정되면_수정버튼을_클릭할_수_있다() {
        composeTestRule.onNodeWithTag("EditCardScreen_CardNumTextField").performTextInput("1234-5678-9012-3456")
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsEnabled()
    }

    @Test
    fun 카드_만료일이_수정되면_수정버튼을_클릭할_수_있다() {
        composeTestRule.onNodeWithTag("EditCardScreen_ExpireDateTextField").performTextInput("12/26")
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsEnabled()
    }

    @Test
    fun 카드_소유자가_수정되면_수정버튼을_클릭할_수_있다() {
        composeTestRule.onNodeWithTag("EditCardScreen_OwnerNameTextField").performTextInput("홍길동")
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsEnabled()
    }

    @Test
    fun 카드_비밀번호가_수정되면_수정버튼을_클릭할_수_있다() {
        composeTestRule.onNodeWithTag("EditCardScreen_PasswordTextField").performTextInput("1234")
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsEnabled()
    }

    @Test
    fun 은행을_선택하면_수정버튼을_클릭할_수_있다() {
        composeTestRule.onNodeWithTag("EditCardScreen_PaymentCard").performClick()
        composeTestRule.onNodeWithText("국민카드").performClick()
        composeTestRule.onNodeWithTag("EditCardTopBar_CheckButton").assertIsEnabled()
    }

}
