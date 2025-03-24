package nextstep.payments.common.model

import androidx.annotation.StringRes
import nextstep.payments.R

sealed class Validation {
    data object Init : Validation()

    sealed class Failure(@StringRes val msgId: Int) : Validation() {
        data object Empty : Failure(R.string.fill_input_field)
        class Error(id: Int) : Failure(id)
    }

    data object Success : Validation()
}
