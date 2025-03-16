package nextstep.payments.edit_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.repository.PaymentCardsRepository

class EditCardViewModel(
    val initializedCard: Card,
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _cardUpdated = MutableStateFlow(false)
    val cardUpdated: StateFlow<Boolean> = _cardUpdated.asStateFlow()

    private val _card = MutableStateFlow(initializedCard)
    val card: StateFlow<Card> = _card.asStateFlow()

    private val _bankType = MutableStateFlow(card.value.bankType)
    val bankType: StateFlow<BankType?> = _bankType.asStateFlow()

    private val _isBottomSheetOpen = MutableStateFlow(false)
    val isBottomSheetOpen: StateFlow<Boolean> = _isBottomSheetOpen.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _card.update { it.copy(cardNumber = cardNumber) }
    }

    fun setExpiredDate(expiredDate: String) {
        _card.update { it.copy(expiredDate = expiredDate) }
    }

    fun setOwnerName(ownerName: String) {
        _card.update { it.copy(ownerName = ownerName) }
    }

    fun setPassword(password: String) {
        _card.update { it.copy(password = password) }
    }

    fun setBankType(bankType: BankType) {
        _card.update { it.copy(bankType = bankType) }
    }

    fun setBottomSheetOpen(isOpen: Boolean) {
        _isBottomSheetOpen.update { isOpen }
    }

    fun updateCard() {
        repository.updateCard(card = card.value)
        _cardUpdated.update { true }
    }

    companion object {
        val CARD_KEY = object : CreationExtras.Key<Card> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                val card = this[CARD_KEY] as Card
                EditCardViewModel(
                    initializedCard = card,
                )
            }
        }
    }
}