package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.CardCompany
import nextstep.payments.data.repository.PaymentCardsRepository

class CardAddViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _card = MutableStateFlow<Card>(Card.Empty)
    val card: StateFlow<Card> = _card.asStateFlow()
    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardCompanyBottomSheet =
        MutableStateFlow<CardCompanyBottomSheetState>(CardCompanyBottomSheetState.Show)

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
        repository.addCard(_card.value)
        _cardAdded.value = true
    }

    fun setCardCompanyBottomSheetState(state: CardCompanyBottomSheetState) {
        _cardCompanyBottomSheet.update { state }
    }

    fun setCardCompany(cardCompany: CardCompany) {
        _card.update { it.copy(company = cardCompany) }
        setCardCompanyBottomSheetState(CardCompanyBottomSheetState.Hide)
    }
}
