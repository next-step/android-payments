package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nextstep.payments.repository.PaymentCardsRepository

class ViewModelFactory : ViewModelProvider.Factory {
    private val repository = PaymentCardsRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardsViewModel::class.java)) {
            return CardsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(NewCardViewModel::class.java)) {
            return NewCardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
