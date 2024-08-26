package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.newcard.NewCardScreen
import nextstep.payments.ui.newcard.NewCardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val viewModel = NewCardViewModel()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewCardScreen(
                viewModel = viewModel,
                navigateToCardList = { },
                onBackClick = {}
            )
        }
    }

    @Test
    fun 카드_번호_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        viewModel.setCardNumber("")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("카드 번호를 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자가_아니면_에러_메시지가_노출된다() {
        viewModel.setCardNumber("1234")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("카드 번호는 16자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자이어야_한다() {
        viewModel.setCardNumber("1234123412341234")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("카드 번호는 16자리 입니다.")
            .assertIsNotDisplayed()
    }

    @Test
    fun 카드_번호는_4자리_마다_하이픈이_붙는다() {
        viewModel.setCardNumber("1234123412341234")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("1234-1234-1234-1234")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        viewModel.setExpiredDate("")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("만료일을 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자가_아니면_에러_메시지가_노출된다() {
        viewModel.setExpiredDate("123")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("만료일은 4자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자이어야_한다() {
        viewModel.setExpiredDate("1234")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("만료일은 4자리 입니다.")
            .assertIsNotDisplayed()
    }

    @Test
    fun 만료일_월_년도_사이에_슬래시가_붙는다() {
        viewModel.setExpiredDate("0824")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("08/24")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호_필드_값이_비어_있으면_에러_메시지가_노출된다() {
        viewModel.setPassword("")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("비밀번호를 입력해주세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자가_아니면_에러_메시지가_노출된다() {
        viewModel.setPassword("123")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("비밀번호는 4자리 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자이어야_한다() {
        viewModel.setPassword("1234")
        viewModel.addCard()
        composeTestRule
            .onNodeWithText("비밀번호는 4자리 입니다.")
            .assertIsNotDisplayed()
    }

}