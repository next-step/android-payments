package nextstep.payments

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.creditcard.CreditCardScreen
import nextstep.payments.creditcard.model.CreditCardUiState
import nextstep.payments.model.CreditCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreditCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val fakeUiState: MutableState<CreditCardUiState> =
        mutableStateOf(CreditCardUiState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            CreditCardScreen(uiState = fakeUiState.value, onNavigateToNewCard = {})
        }
    }

    @Test
    fun 로딩_상태일때는_카드_리스트가_노출되지_않는다() {
        // when
        fakeUiState.value = CreditCardUiState.Loading

        // then
        composeTestRule
            .onNodeWithTag("Loading")
            .assertExists()
    }

    @Test
    fun 카드가_없는_상태일때는_카드_등록_유도_화면이_노출된다() {
        // when
        fakeUiState.value = CreditCardUiState.Empty

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()
    }

    @Test
    fun 카드가_1개_주어지면_카드_1개_화면이_노출된다() {
        // when
        fakeUiState.value = CreditCardUiState.One(
            card = card,
        )

        // then
        composeTestRule
            .onNodeWithText("김무일")
            .assertExists()
    }

    @Test
    fun 카드가_2개_이상_주어지면_카드_n개_화면이_노출된다() {
        // when
        fakeUiState.value = CreditCardUiState.Many(
            cards = cards,
        )

        // then
        composeTestRule
            .onNodeWithText("김무일")
            .assertExists()
        composeTestRule
            .onNodeWithText("김무이")
            .assertExists()
    }

    companion object {
        private val card = CreditCard(
            number = "1234567812345678",
            expiredDate = "0428",
            ownerName = "김무일",
            password = "1234"
        )
        private val cards =
            listOf(
                CreditCard(
                    number = "1234567812345678",
                    expiredDate = "0428",
                    ownerName = "김무일",
                    password = "1234"
                ),
                CreditCard(
                    number = "2234567812345678",
                    expiredDate = "0428",
                    ownerName = "김무이",
                    password = "1234"
                ),
                CreditCard(
                    number = "3234567812345678",
                    expiredDate = "0428",
                    ownerName = "김무삼",
                    password = "1234"
                ),
                CreditCard(
                    number = "4234567812345678",
                    expiredDate = "0428",
                    ownerName = "김무사",
                    password = "1234"
                )
            )
    }
}
