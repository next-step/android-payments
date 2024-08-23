package nextstep.payments.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.PaymentCardModel

object PaymentCardRepository {
    private val _cardsStream: MutableStateFlow<List<PaymentCardModel>> = MutableStateFlow(emptyList())
    val cardsStream: StateFlow<List<PaymentCardModel>> = _cardsStream.asStateFlow()

    fun addCard(card: PaymentCardModel) {
        _cardsStream.update {
            val index = it.indexOfFirst { it.cardNumber == card.cardNumber }
            if (index >= 0) {
                it.toMutableList().apply {
                    set(index, card)
                }
            } else {
                it + card
            }
        }
    }
}
