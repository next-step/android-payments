package nextstep.payments

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.creditcard.CreditCardContent
import nextstep.payments.ui.creditcard.model.CreditCardUiState
import nextstep.payments.ui.model.CreditCardType.RegisteredCard
import nextstep.payments.ui.newcard.model.CardCompany
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
            CreditCardContent(uiState = fakeUiState.value, onNavigateToNewCard = {})
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
        private val card = RegisteredCard(
            number = "1234123412341234",
            expiredDate = "0428",
            ownerName = "김무일",
            password = "1234",
            cardCompany = CardCompany(
                imageId = 9652,
                name = "Rosemary Richard",
                color = 5265
            ),

        )
        private val cards =
            List(2) { index ->
                RegisteredCard(
                    number = "123412341234123$index",
                    expiredDate = "0428",
                    ownerName = if (index == 0) "김무일" else "김무이",
                    password = "1234",
                    cardCompany = CardCompany(
                        imageId = 9652,
                        name = "Rosemary Richard",
                        color = 5265
                    ),

                )
            }
    }
}
