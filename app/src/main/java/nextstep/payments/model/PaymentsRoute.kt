package nextstep.payments.model

import kotlinx.serialization.Serializable

sealed class PaymentsRoute {
    @Serializable
    data object List: PaymentsRoute()

    @Serializable
    data object Add: PaymentsRoute()
}
