package nextstep.payments.new_card

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.data.BankType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenStatelessTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var cardNumber by mutableStateOf("")
    private var expiredDate by mutableStateOf("")
    private var ownerName by mutableStateOf("")
    private var password by mutableStateOf("")
    private var bankType by mutableStateOf<BankType?>(null)
    private var isBottomSheetOpen by mutableStateOf(true)

    @Before
    fun setUp() {
        cardNumber = ""
        expiredDate = ""
        ownerName = ""
        password = ""
        bankType = null
        isBottomSheetOpen = true

        // given
        composeTestRule.setContent {
            val isCompleteButtonEnabled by remember {
                derivedStateOf {
                    cardNumber.isNotEmpty() && expiredDate.isNotEmpty() && ownerName.isNotEmpty() && password.isNotEmpty() && bankType != null
                }
            }

            NewCardScreen(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                bankType = bankType,
                isBottomSheetOpen = isBottomSheetOpen,
                isCompleteButtonEnabled = isCompleteButtonEnabled,
                onBackClick = {},
                addCard = {},
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBottomSheetOpen = {},
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
        cardNumber = "0000"

        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("0000")
    }

    @Test
    fun `만료일_입력_필드에_입력한_값과_expiredDate가_같아야_한다`() {
        // when
        expiredDate = "00"

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("00")
    }

    @Test
    fun `카드_소유자_입력_필드에_입력한_값과_ownerName가_같아야_한다`() {
        // when
        ownerName = "홍길동"

        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertTextContains("홍길동")
    }

    @Test
    fun `비밀번호_입력_필드에_입력한_값이_마스킹_돼서_노출되어야_한다`() {
        // when
        password = "1234"

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains("••••")
    }

    @Test
    fun `카드추가_페이지에_진입_시_카드사_선택_바텀시트가_보여야한다`() {
        // then
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("Card_BankSelectBottomSheet")
            .assertIsDisplayed()
    }


    @Test
    fun `카드사와_모든_입력_5개가_비어있지_않으면_카드_등록_버튼이_활성화_된다`() {
        // when
        cardNumber = "1111222233334444"
        expiredDate = "1122"
        ownerName = "이름"
        password = "1234"
        bankType = BankType.KB

        // then
        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }
}