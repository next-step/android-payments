package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.CardCompany
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.utils.ResultCode

class CardAddViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _card = MutableStateFlow<Card>(Card.Empty)
    val card: StateFlow<Card> = _card
    private val _navigateToCardList = MutableStateFlow<Boolean>(false)
    val navigateToCardList: StateFlow<Boolean> = _navigateToCardList
    private val _showToast = MutableStateFlow<ResultCode>(ResultCode.IDLE)
    val showToast: StateFlow<ResultCode> = _showToast.asStateFlow()

    private lateinit var initialCard: Card

    private val _cardCompanyBottomSheet =
        MutableStateFlow<CardCompanyBottomSheetState>(CardCompanyBottomSheetState.Hide)

    val cardCompanyBottomSheet: StateFlow<CardCompanyBottomSheetState> =
        _cardCompanyBottomSheet.asStateFlow()

    fun setCardNumber(value: String) {
        _card.update {
            it.copy(number = value)
        }
    }

    fun setExpiredDate(value: String) {
        _card.update {
            it.copy(expiredDate = value)
        }
    }

    fun setOwnerName(value: String) {
        _card.update {
            it.copy(ownerName = value)
        }
    }

    fun setPassword(value: String) {
        _card.update {
            it.copy(password = value)
        }
    }

    fun addCard() {
        if (_card.value.company != null) {
            repository.addCard(_card.value)
            showToast(ResultCode.SUCCESS_ADD_CARD)
            navigateToCardList()
        } else {
            showToast(ResultCode.SELECT_CARD_COMPANY)
            setCardCompanyBottomSheetState(CardCompanyBottomSheetState.Show)
        }
    }

    fun modifyCard() {
        if (initialCard == _card.value) {
            showToast(ResultCode.NOTHING_TO_MODIFY)
        } else {
            repository.update(card = _card.value)
            showToast(ResultCode.SUCCESS_MODIFY_CARD)
            navigateToCardList()
        }
    }

    fun setCardCompanyBottomSheetState(state: CardCompanyBottomSheetState) {
        _cardCompanyBottomSheet.update { state }
    }

    fun setCardCompany(cardCompany: CardCompany) {
        _card.update { it.copy(company = cardCompany) }
        setCardCompanyBottomSheetState(CardCompanyBottomSheetState.Hide)
    }

    fun initializeCard(cardId: String): Card {
        initialCard = repository.getCardOrNull(cardId) ?: Card.Empty

        updateCard(initialCard)
        if (initialCard == Card.Empty) setCardCompanyBottomSheetState(CardCompanyBottomSheetState.Show)

        return initialCard
    }

    private fun showToast(resultCode: ResultCode) {
        _showToast.update { resultCode }
    }

    private fun updateCard(card: Card) {
        _card.update { card }
    }

    private fun navigateToCardList() {
        _navigateToCardList.update { true }
    }
}
