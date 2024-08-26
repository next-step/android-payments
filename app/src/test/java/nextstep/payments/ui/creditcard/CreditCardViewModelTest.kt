package nextstep.payments.ui.creditcard

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.model.CardRegisterResult
import org.junit.Before
import org.junit.Test

class CreditCardViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        PaymentCardsRepository.clear()
    }

    @Test
    fun 등록된_카드가_없으면_EMPTY_상태를_반환한다() =
        runTest {
            // given
            val viewModel = CreditCardViewModel()

            // when && then
            viewModel.uiState.test {
                assertEquals(CreditCardUiState.Empty, awaitItem())
            }
        }

    @Test
    fun 등록된_카드가_하나면_ONE_상태를_반환한다() =
        runTest {
            // given
            PaymentCardsRepository.addCard(
                Card(
                    brand = Brand.BC,
                    cardNumber = "1234-1234-1234-1234",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
            )
            val viewModel = CreditCardViewModel()

            // when && then
            viewModel.uiState.test {
                assertTrue(awaitItem() is CreditCardUiState.One)
            }
        }

    @Test
    fun 등록된_카드가_여러개면_MANY_상태를_반환한다() =
        runTest {
            // given
            PaymentCardsRepository.addCard(
                Card(
                    brand = Brand.BC,
                    cardNumber = "1234-1234-1234-1234",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
            )
            PaymentCardsRepository.addCard(
                Card(
                    brand = Brand.SHINHAN,
                    cardNumber = "1234-1234-1234-1234",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
            )
            val viewModel = CreditCardViewModel()

            // when && then
            viewModel.uiState.test {
                assertTrue(awaitItem() is CreditCardUiState.Many)
            }
        }

    @Test
    fun 카드_등록_성공시_카드_목록을_다시_가져온다() =
        runTest {
            // given
            val viewModel = CreditCardViewModel()
            val card =
                Card(
                    brand = Brand.BC,
                    cardNumber = "1234-1234-1234-1234",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ).let {
                    val new = PaymentCardsRepository.addCard(it)
                    new
                }

            // when
            viewModel.handleCardRegisterResult(CardRegisterResult.SUCCESS)

            viewModel.uiState.test {
                // then
                val item = awaitItem()
                assertTrue(item is CreditCardUiState.One)
                val one = item as CreditCardUiState.One
                assertEquals(card, one.card)
            }
        }

    @Test
    fun 카드_수정_성공_시_카드_목록을_다시_가져온다() =
        runTest {
            // given
            val card =
                Card(
                    brand = Brand.BC,
                    cardNumber = "1234-1234-1234-1234",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ).let {
                    val new = PaymentCardsRepository.addCard(it)
                    new
                }
            val viewModel = CreditCardViewModel()

            // when
            val updatedCard = card.copy(ownerName = "김길동")
            PaymentCardsRepository.updateCard(updatedCard)
            viewModel.handleCardRegisterResult(CardRegisterResult.SUCCESS)

            // then
            viewModel.uiState.test {
                val item = awaitItem()
                assertTrue(item is CreditCardUiState.One)
                val one = item as CreditCardUiState.One
                assertEquals(updatedCard, one.card)
            }
        }

    @Test
    fun 카드_추가_등록_성공_시_카드_목록을_다시_가져온다() =
        runTest {
            // given
            val expectedCards = mutableListOf<Card>()
            Card(
                brand = Brand.BC,
                cardNumber = "1234-1234-1234-1234",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            ).also {
                expectedCards.add(PaymentCardsRepository.addCard(it))
            }
            val viewModel = CreditCardViewModel()

            // when
            Card(
                brand = Brand.SHINHAN,
                cardNumber = "1234-1234-1234-1234",
                expiredDate = "12/34",
                ownerName = "김길동",
                password = "1234",
            ).also {
                expectedCards.add(PaymentCardsRepository.addCard(it))
            }
            viewModel.handleCardRegisterResult(CardRegisterResult.SUCCESS)

            viewModel.uiState.test {
                val item = awaitItem()
                assertTrue(item is CreditCardUiState.Many)
                val many = item as CreditCardUiState.Many
                assertEquals(expectedCards, many.cards)
            }
        }

    @Test
    fun 새로운_카드_등록_이벤트가_발생하면_카드_등록_화면으로_이동한다() =
        runTest {
            // given
            val viewModel = CreditCardViewModel()

            viewModel.effect.test {
                // when
                viewModel.dispatchEvent(CreditCardEvent.OnNewCardClick)

                // then
                assertEquals(CreditCardEffect.NavigateToRegisterCard(null), awaitItem())
            }
        }
}
