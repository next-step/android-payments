package nextstep.payments.new_card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenStatelessTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var cardNumber = ""
    private var expiredDate = ""
    private var ownerName = ""
    private var password = ""

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            var cardNumber by remember { mutableStateOf("") }
            var expiredDate by remember { mutableStateOf("") }
            var ownerName by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            this.cardNumber = ""
            this.expiredDate = ""
            this.ownerName = ""
            this.password = ""

            NewCardScreen(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                onBackClick = { },
                addCard = { },
                setCardNumber = {
                    cardNumber = it
                    this.cardNumber = it
                },
                setExpiredDate = {
                    expiredDate = it
                    this.expiredDate = it
                },
                setOwnerName = {
                    ownerName = it
                    this.ownerName = it
                },
                setPassword = {
                    password = it
                    this.password = it
                },
            )
        }
    }

    @Test
    fun `카드_번호_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호_입력_필드가_노출되어야_한다`() {
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_번호_입력_필드에_입력한_값과_cardNumber가_같아야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput("0000")

        // then
        assertEquals("0000", cardNumber)
    }

    @Test
    fun `만료일_입력_필드에_입력한_값과_expiredDate가_같아야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput("00")

        // then
        assertEquals("00", expiredDate)
    }

    @Test
    fun `카드_소유자_입력_필드에_입력한_값과_ownerName가_같아야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput("홍길동")

        // then
        assertEquals("홍길동", ownerName)
    }

    @Test
    fun `비밀번호_입력_필드에_입력한_값과_password가_같아야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput("1111")

        // then
        assertEquals("1111", password)
    }

    @Test
    fun `비밀번호_입력_필드에_입력한_값이_마스킹_돼서_노출되어야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput("1234")

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains("••••")
    }
}