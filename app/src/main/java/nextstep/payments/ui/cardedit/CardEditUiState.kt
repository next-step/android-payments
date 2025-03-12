package nextstep.payments.ui.cardedit

import nextstep.payments.model.CreditCard
import nextstep.payments.ui.form.PaymentCardFormState

sealed interface CardEditUiState {
    data object Loading : CardEditUiState
    data class Success(
        val creditCard: CreditCard,
        val formState: PaymentCardFormState,
    ) : CardEditUiState
}
