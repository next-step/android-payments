package nextstep.payments.ui.add

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CardAddScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `미완성_카드가_보여진다`() {
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("미완성 카드")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_번호는_포맷에_맞춰_보여진다`() {
        val cardNumber = "1111222233334444"
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = cardNumber,
                expiredDate = "",
                ownerName = "",
                password = "",
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_포맷에_맞춰_보여진다`() {
        val expiredDate = "1224"
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = "",
                expiredDate = expiredDate,
                ownerName = "",
                password = "",
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("12 / 24")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_평문으로_보이지_않는다`() {
        val password = "12341234"
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = password,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithText("12341234")
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("••••••••")
            .assertIsDisplayed()
    }

    @Test
    fun `뒤로가기_버튼은_클릭_가능하다`() {
        var clicked: Boolean = false
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = { clicked = true },
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        assert(clicked == true)
    }

    @Test
    fun `완료_버튼은_클릭_가능하다`() {
        var clicked: Boolean = false
        composeTestRule.setContent {
            CardAddScreen(
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = { },
                onSaveClick = { clicked = true },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        assert(clicked == true)
    }

}
