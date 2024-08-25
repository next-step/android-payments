package nextstep.payments.screen.card.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.data.BcCard
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.card.list.CardListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreditCardScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        PaymentCardsRepository.removeAllCard()
    }

    @Test
    fun 스크린_상단에_Payments_타이틀이_노출된다() {
        // when : cardListScreen을 setContent 한다.
        composeRule
            .setContent {
                CardListScreen(
                    cards = PaymentCardsRepository.cards,
                    onAddCard = {}
                )
            }

        // then : Payments 타이틀에 노출되어야한다.
        composeRule
            .onNodeWithText("Payments")
            .assertExists()
    }

    @Test
    fun 등록된_카드가_존재할_경우_해당_카드_수만큼_카드_이미지를_노출시키다() {
        // given : 두 개의 카드를 등록한다
        PaymentCardsRepository.addCard(
            Card(
                cardNumber = "1234-5678-1234-5678",
                ownerName = "홍길동",
                expiredDate = "12/24",
                password = "123",
                cardCompany = BcCard
            )
        )

        PaymentCardsRepository.addCard(
            Card(
                cardNumber = "1234-5678-1234-5628",
                ownerName = "홍길동",
                expiredDate = "12/24",
                password = "123",
                cardCompany = BcCard
            )
        )

        // when : 화면을 렌더링한다.
        composeRule.setContent {
            CardListScreen(
                cards = PaymentCardsRepository.cards
            )
        }
        val actual = composeRule
            .onAllNodesWithTag("cardImage")
            .fetchSemanticsNodes().size

        // then : 카드 이미지 두개가 노출되어야 한다.
        assert(actual == 2)
    }

    @Test
    fun 등록된_카드가_존재하지_않을_경우_해당_카드_이미지를_노출하지_않는다() {
        // when : 화면을 렌더링한다.
        composeRule.setContent {
            CardListScreen(
                cards = PaymentCardsRepository.cards
            )
        }

        // then : 카드 이미지가 노출되지 않는다.
        composeRule
            .onNodeWithTag("cardImage")
            .assertDoesNotExist()
    }

    @Test
    fun 등록된_카드가_존재하지_않을_경우_카드_추가_멘트가_노출된다() {
        // given : 카드 등록이 되어있지 않다.


        // when : 화면을 렌더링한다.
//        cardImage
        composeRule.setContent {
            CardListScreen(
                cards = PaymentCardsRepository.cards
            )
        }

        // then : 카드 추가 멘트가 노출된다.
        composeRule
            .onNodeWithTag("textComment")
            .assertExists()
    }

    @Test
    fun 등록된_카드가_존재할_경우_카드_추가_멘트가_노출되자_않는다() {
        // given : 카드 등록이 되어있다.
        PaymentCardsRepository.addCard(
            Card(
                cardNumber = "1234-5678-1234-5628",
                ownerName = "홍길동",
                expiredDate = "12/24",
                password = "123",
                cardCompany = BcCard
            )
        )

        // when : 화면을 렌더링한다.
        composeRule.setContent {
            CardListScreen(
                cards = PaymentCardsRepository.cards
            )
        }

        // then : 카드 추가 멘트가 노출되지 않는다
        composeRule
            .onNodeWithTag("textComment")
            .assertDoesNotExist()
    }
}
