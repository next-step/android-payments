package nextstep.payments.ui.component

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.data.model.BankType
import org.junit.Rule
import org.junit.Test

class CardFormScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_미리보기가_보여진다`() {
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                bankSelectOpened = false,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
                onBackClick = {},
                onSaveClick = {},

            )
        }

        composeTestRule
            .onNodeWithContentDescription("카드 미리보기")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_번호_입력시_포맷에_맞춰_보여진다`() {
        val cardNumber = "1111222233334444"
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = cardNumber,
                expiredDate = "",
                ownerName = "",
                password = "",
                bankType = BankType.NOT_SELECTED,
                bankSelectOpened = false,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("카드 번호 입력")
            .assert(hasText("1111 - 2222 - **** - ****"))
    }

    @Test
    fun `만료일_입력시_포맷에_맞춰_보여진다`() {
        val expiredDate = "1224"
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = expiredDate,
                ownerName = "",
                password = "",
                bankSelectOpened = false,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("만료일 입력")
            .assert(hasText("12 / 24"))
    }

    @Test
    fun `비밀번호는_평문으로_보이지_않는다`() {
        val password = "12341234"
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = password,
                bankSelectOpened = false,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
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
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                bankSelectOpened = false,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
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
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                bankSelectOpened = false,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
                onBackClick = { },
                onSaveClick = { clicked = true },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        assert(clicked == true)
    }

    @Test
    fun `카드사를_선택하지_않으면_카드_선택_목록이_보인다`() {
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                bankSelectOpened = true,
                bankType = BankType.NOT_SELECTED,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBankSelectOpened = {},
                onBackClick = { },
                onSaveClick = { },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("카드사 선택 목록 보기")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하면_카드_선택_목록이_보아지_않는다`() {
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                bankSelectOpened = false,
                bankType = BankType.SINHAN,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = { },
                setBankSelectOpened = {},
                onBackClick = { },
                onSaveClick = { },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("카드사 선택 목록 보기")
            .assertIsNotDisplayed()
    }

    @Test
    fun `입력한_정보가_포맷에_맞춰_카드_미리보기에_보인다`() {
        composeTestRule.setContent {
            CardFormScreen(
                title = "",
                cardNumber = "0000111122223333",
                expiredDate = "1023",
                ownerName = "김씨",
                password = "1234",
                bankSelectOpened = false,
                bankType = BankType.KAKAO,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = { },
                setBankSelectOpened = {},
                onBackClick = { },
                onSaveClick = { },
            )
        }

        val cardPreview = composeTestRule
            .onNodeWithContentDescription("카드 미리보기")

        cardPreview.assertTextContains("0000 - 1111 - **** - ****")
        cardPreview.assertTextContains("10 / 23")
        cardPreview.assertTextContains("김씨")
    }
}
