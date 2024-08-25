package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.card.CardListScreen
import nextstep.payments.ui.card.CardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val viewModel = CardViewModel()
    private val repository: PaymentCardsRepository = PaymentCardsRepository

    @Before
    fun setup() {
        composeTestRule.setContent {
            CardListScreen(
                viewModel = viewModel,
                onAddPaymentCard = { }
            )
        }
    }

    @Test
    fun 카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출된다() {
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_카드가_한_개_있을_때의_카드_추가_UI는_목록_하단에_노출된다() {
        val card = Card(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = ""
        )
        repository.addCard(card)
        viewModel.fetchCards()

        composeTestRule
            .onNodeWithTag("Card")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 추가")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_카드가_여러_개_있을_때의_카드_추가_UI는_상단바에_노출된다() {
        val card = Card(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = ""
        )
        repository.addCard(card)
        repository.addCard(card)
        viewModel.fetchCards()
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }
}