package nextstep.payments.ui.update

import androidx.lifecycle.SavedStateHandle
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.updatecard.UpdateCardViewModel
import nextstep.payments.util.JsonConfig
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.YearMonth

class UpdateCardViewModelTest {

    @Test
    fun `클릭한_카드의_타입이_잘_전달되어야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertTrue(updateCardViewModel.uiState.value.selectedBank == card.type)
    }

    @Test
    fun `카드변경의_초기값이_false이어야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertFalse(updateCardViewModel.uiState.value.cardUpdated)

    }

    @Test
    fun `클릭한_카드의_카드번호가_잘_전달되어야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "2345234523452345",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "2345",
            password = "2345"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertTrue(updateCardViewModel.uiState.value.cardNumber == card.number)

    }

    @Test
    fun `클릭한_카드의_유효기간_잘_전달되어야_한다`() {

        //given
        val card = Card(
            type = BankType.HANA,
            number = "1235123412351234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "5678",
            password = "5678"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertTrue(updateCardViewModel.uiState.value.expiredDate == card.getStringExpiredDate())
    }

    @Test
    fun `클릭한_카드의_사용자이름이_잘_전달되어야_한다`() {
        //given
        val card = Card(
            type = BankType.WOORI,
            number = "6789768967896789",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "6789",
            password = "6789"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertTrue(updateCardViewModel.uiState.value.ownerName == card.ownerName)
    }

    @Test
    fun `클릭한_카드의_비밀번호가_잘_전달되어야_한다`() {

        //given
        val card = Card(
            type = BankType.BC,
            number = "5678567856785678",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "5678",
            password = "5678"
        )

        //when
        val updateCardViewModel = createViewModel(card)

        //then
        assertTrue(updateCardViewModel.uiState.value.password == card.password)
    }

    @Test
    fun `클릭한_카드의_카드번호가_변경되어야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)

        //when
        updateCardViewModel.setCardNumber("2345234523452345")

        //then
        assertTrue(updateCardViewModel.uiState.value.cardNumber == "2345234523452345")
    }

    @Test
    fun `클릭한_카드의_유효기간이_변경되어야_한다`() {

        //given
        val card = Card(
            type = BankType.HYUNDAI,
            number = "1354135413541354",
            expiredDate = YearMonth.of(25, 12),
            ownerName = "1354",
            password = "1354"
        )
        val updateCardViewModel = createViewModel(card)

        //when
        updateCardViewModel.setExpiredDate("1234")

        //then
        assertTrue(updateCardViewModel.uiState.value.expiredDate == "1234")
    }

    @Test
    fun `클릭한_카드의_사용자이름이_변경되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KB,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)

        //when
        updateCardViewModel.setOwnerName("abcd")

        //then
        assertTrue(updateCardViewModel.uiState.value.ownerName == "abcd")
    }

    @Test
    fun `클릭한_카드의_비밀번호가_변경되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)

        //when
        updateCardViewModel.setPassword("5678")

        //then
        assertTrue(updateCardViewModel.uiState.value.password == "5678")
    }

    @Test
    fun `카드번호를_변경하면_카드수정이_되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)
        updateCardViewModel.setCardNumber("2345234523452345")

        //when
        updateCardViewModel.updateCard()

        //then
        assertTrue(updateCardViewModel.uiState.value.cardUpdated)

    }

    @Test
    fun `유효기간을_변경하면_카드수정이_되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)
        updateCardViewModel.setExpiredDate("1234")

        //when
        updateCardViewModel.updateCard()

        //then
        assertTrue(updateCardViewModel.uiState.value.cardUpdated)

    }

    @Test
    fun `사용자이름을_변경하면_카드수정이_되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)
        updateCardViewModel.setOwnerName("abcd")

        //when
        updateCardViewModel.updateCard()

        //then
        assertTrue(updateCardViewModel.uiState.value.cardUpdated)


    }

    @Test
    fun `비밀번호를_변경하면_카드수정이_되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1234",
            password = "1234"
        )
        val updateCardViewModel = createViewModel(card)
        updateCardViewModel.setPassword("3456")

        //when
        updateCardViewModel.updateCard()

        //then
        assertTrue(updateCardViewModel.uiState.value.cardUpdated)


    }

    @Test
    fun `변경내용이_없으면_수정이_안되어야_한다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "1234123412341234",
            expiredDate = YearMonth.of(2024, 12),
            ownerName = "1234",
            password = "1234"
        )

        val paymentRepository = PaymentCardsRepository.apply {
            upsertCard(card)
        }
        val updateCardViewModel = createViewModel(card, paymentRepository)

        //when
        updateCardViewModel.updateCard()

        //then
        assertFalse(updateCardViewModel.uiState.value.cardUpdated)
    }


    companion object {

        private const val KEY_CARD_ITEM = "key_card_item"

        fun createViewModel(
            card: Card,
            repository: PaymentCardsRepository = PaymentCardsRepository
        ): UpdateCardViewModel {
            val savedStateHandle = SavedStateHandle().apply {
                set(KEY_CARD_ITEM, JsonConfig.json.encodeToString(card))
            }
            return UpdateCardViewModel(
                savedStateHandle = savedStateHandle,
                paymentCardsRepository = repository
            )
        }
    }
}