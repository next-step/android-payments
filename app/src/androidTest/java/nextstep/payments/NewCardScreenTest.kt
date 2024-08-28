package nextstep.payments

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.newcard.NewCardScreen
import nextstep.payments.ui.newcard.NewCardUiState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val newCardUiState = mutableStateOf(NewCardUiState(isInitialInput = true))

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewCardScreen(
                newCardUiState = newCardUiState.value,
                onBankSelect = {},
                onBackClick = { /*TODO*/ },
                onSaveClick = { /*TODO*/ },
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {}
            )
        }
    }

    @Test
    fun 카드_번호_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("카드 번호를 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자가_아니면_에러_메시지가_노출된다() {
        newCardUiState.value = newCardUiState.value.copy(cardNumber = "123412341234")
        composeTestRule
            .onNodeWithText("카드 번호는 16자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자이어야_한다() {
        newCardUiState.value = newCardUiState.value.copy(cardNumber = "1234123412341234")
        composeTestRule
            .onNodeWithText("카드 번호는 16자리 입니다.")
            .assertIsNotDisplayed()
    }

    @Test
    fun 카드_번호는_4자리_마다_하이픈이_붙는다() {
        newCardUiState.value = newCardUiState.value.copy(cardNumber = "1234123412341234")
        composeTestRule
            .onNodeWithText("1234-1234-1234-1234")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("만료일을 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자가_아니면_에러_메시지가_노출된다() {
        newCardUiState.value = newCardUiState.value.copy(expiredDate = "123")
        composeTestRule
            .onNodeWithText("만료일은 4자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자이어야_한다() {
        newCardUiState.value = newCardUiState.value.copy(expiredDate = "1234")
        composeTestRule
            .onNodeWithText("만료일은 4자리 입니다.")
            .assertIsNotDisplayed()
    }

    @Test
    fun 만료일_월_년도_사이에_슬래시가_붙는다() {
        newCardUiState.value = newCardUiState.value.copy(expiredDate = "0824")
        composeTestRule
            .onNodeWithText("08/24")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("비밀번호를 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자가_아니면_에러_메시지가_노출된다() {
        newCardUiState.value = newCardUiState.value.copy(password = "123")
        composeTestRule
            .onNodeWithText("비밀번호는 4자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자이어야_한다() {
        newCardUiState.value = newCardUiState.value.copy(password = "1234")
        composeTestRule
            .onNodeWithText("비밀번호는 4자리 입니다.")
            .assertIsNotDisplayed()
    }

    @Test
    fun 카드_추가_화면에_접속했을_때_카드사_선택_바텀_시트가_노출된다() {
        composeTestRule
            .onNodeWithTag("cardInfoBottomSheet")
            .assertIsDisplayed()
    }

    @Test
    fun 카드사를_선택하면_바텀_시트가_사라진다() {
        newCardUiState.value = newCardUiState.value.copy(isCardSelected = true, isBottomSheetVisible = false)
        composeTestRule
            .onNodeWithTag("cardInfoBottomSheet")
            .assertIsNotDisplayed()
    }

}
