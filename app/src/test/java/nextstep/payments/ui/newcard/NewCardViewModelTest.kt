package nextstep.payments.ui.newcard

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType
import org.junit.Test

class NewCardViewModelTest {

    @Test
    fun `카드추가여부의_초기값은_false이어야_한다`() {

        //given, when
        val newCardViewModel = NewCardViewModel()

        //then
        assertFalse(newCardViewModel.uiState.value.cardAdded)
    }

    @Test
    fun `카드_타입이_올바르게_변경되어야_한다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setBankType(BankType.KB)

        //then
        assertTrue(newCardViewModel.uiState.value.selectedBank == BankType.KB)
    }

    @Test
    fun `카드번호가_올바르게_변경되어야_한다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setCardNumber("1234")

        //then
        assertTrue(newCardViewModel.uiState.value.cardNumber == "1234")
    }

    @Test
    fun `카드번호는_16자까지_입려되어진다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setCardNumber("1234123412341234" + "234")

        //then
        assertTrue(newCardViewModel.uiState.value.cardNumber == "1234123412341234")
    }

    @Test
    fun `유효기간이_올바르게_변경되어야_한다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setExpiredDate("04")

        //then
        assertTrue(newCardViewModel.uiState.value.expiredDate == "04")
    }

    @Test
    fun `유효기간은_4자까지_입려되어진다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setExpiredDate("0424" + "25")

        //then
        assertTrue(newCardViewModel.uiState.value.expiredDate == "0424")
    }

    @Test
    fun `사용자이름이_올바르게_변경되어야_한다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setOwnerName("qwer")

        //then
        assertTrue(newCardViewModel.uiState.value.ownerName == "qwer")
    }

    @Test
    fun `비밀번호가_올바르게_변경되어야_한다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setPassword("1234")

        //then
        assertTrue(newCardViewModel.uiState.value.password == "1234")
    }

    @Test
    fun `비밀번호는_4자까지_입려되어진다`() {
        //given
        val newCardViewModel = NewCardViewModel()

        //when
        newCardViewModel.setPassword("1234" + "12")

        //then
        assertTrue(newCardViewModel.uiState.value.password == "1234")
    }

    @Test
    fun `카드리스트에_없는경우_카드추가가_되어야_한다`() {
        //given
        val repository = PaymentCardsRepository
        repository.clear()

        val viewModel = NewCardViewModel(repository)

        viewModel.setBankType(BankType.KB)
        viewModel.setCardNumber("1234123412341234")
        viewModel.setExpiredDate("0424")
        viewModel.setOwnerName("qwer")
        viewModel.setPassword("1234")

        //when
        viewModel.addCard()

        //then
        assertTrue(viewModel.uiState.value.cardAdded)
    }

}