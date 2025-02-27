package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.screens.card.list.CardListScreen
import nextstep.payments.screens.card.list.CardListViewModel
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출되어야_한다() {
        // given
        val paymentCardsRepository = FakePaymentCardsRepository(emptyList())

        composeTestRule.setContent {
            CardListScreen(
                onAddCardClick = {},
                viewModel = CardListViewModel(
                    paymentCardsRepository = paymentCardsRepository,
                ),
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_목록에_카드가_한_개_있을_때의_카드_추가_UI는_목록_하단에_노출된다() {
        // given
        val paymentCardsRepository = FakePaymentCardsRepository(
            FakePaymentCardsRepository.fakeCards.subList(0, 1)
        )

        composeTestRule.setContent {
            CardListScreen(
                onAddCardClick = {},
                viewModel = CardListViewModel(
                    paymentCardsRepository = paymentCardsRepository,
                ),
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드추가")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_목록에_카드가_여러_개_있을_때의_카드_추가_UI는_상단바에_노출된다() {
        // given
        val paymentCardsRepository =
            FakePaymentCardsRepository(FakePaymentCardsRepository.fakeCards)
        composeTestRule.setContent {
            CardListScreen(
                onAddCardClick = {},
                viewModel = CardListViewModel(
                    paymentCardsRepository = paymentCardsRepository,
                )
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드추가")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("추가")
            .assertExists()
    }
}
