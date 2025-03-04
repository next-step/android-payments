package nextstep.payments.edit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository

class EditViewModel(
    private val cardId: Int,
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : BaseViewModel<EditState, EditEvent, EditSideEffect>() {

    init {
        repository.getCard(cardId)?.let {
            updateState {
                copy(
                    id = it.id,
                    cardNumber = it.cardNumber,
                    expiredDate = it.expiredDate,
                    ownerName = it.ownerName,
                    password = it.password,
                    bankType = it.bankType
                )
            }
        }
    }

    override fun initState(): EditState = EditState(id = cardId)

    override fun handleEvent(event: EditEvent) {
        when(event) {
            EditEvent.OnClickBackButton -> sendSideEffect(EditSideEffect.NavigateBack)
            EditEvent.OnClickCompleteButton -> sendSideEffect(EditSideEffect.NavigateBackWithNeedReload)
        }
    }

    companion object {
        fun getFactory(cardInt: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(cardInt)
            }
        }
    }
}