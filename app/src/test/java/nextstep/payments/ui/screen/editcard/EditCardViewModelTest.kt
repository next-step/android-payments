package nextstep.payments.ui.screen.editcard

import kotlinx.coroutines.test.runTest
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.creditcard.model.CardModel
import nextstep.payments.ui.screen.creditcard.model.toData
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class EditCardViewModelTest {
    private lateinit var viewModel: EditCardViewModel
    private val repository = PaymentCardsRepository

    private val originalCard = CardModel(
        id = "1",
        cardNumber = "1234",
        cardOwnerName = "lee",
        cardExpiredDate = "12/25",
        cardPassword = "1234",
        bankType = BankTypeModel.KAKAO
    )

    @Before
    fun setup() {
        viewModel = EditCardViewModel()
        repository.addCard(originalCard.toData())
        viewModel.handleEvent(EditCardEvent.OnInit("1"))
    }

    @After
    fun tearDown() {
        repository.remove(originalCard.id)
    }

    @Test
    fun 변경사항이_없을_때_저장이_발생하지_않고_메시지가_설정되어야_한다() = runTest {
        viewModel.handleEvent(EditCardEvent.OnSaveClicked)

        assertEquals("변경사항이 없습니다.", viewModel.state.value.message)
    }

    @Test
    fun 변경사항이_있을_때_카드가_저장되어야_한다() = runTest {
        viewModel.handleEvent(EditCardEvent.OnCardNumberChanged("5678"))
        viewModel.handleEvent(EditCardEvent.OnSaveClicked)

        val updatedCard = PaymentCardsRepository.getCard("1")
        assertEquals("5678", updatedCard?.cardNumber)
        assertEquals(viewModel.state.value.saved, true)
    }
}
