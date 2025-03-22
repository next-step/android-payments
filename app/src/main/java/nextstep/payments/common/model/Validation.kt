package nextstep.payments.common.model

import nextstep.payments.R

sealed class Validation {
    data object Init : Validation()

    sealed class Failure(val msgId: Int) : Validation() {
        data object Empty : Failure(R.string.fill_input_field)
        class Error(id: Int) : Failure(id)
    }

    data object Success : Validation()
}
