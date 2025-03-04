package nextstep.payments.edit

import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository

class EditViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : BaseViewModel<EditState, EditEvent, EditSideEffect>() {

    override fun initState(): EditState {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: EditEvent) {
        TODO("Not yet implemented")
    }
}