package nextstep.payments.ui.card.registration

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface RegistrationUiState : Parcelable {
    @Parcelize
    data object NewCard : RegistrationUiState

    @Parcelize
    data object EditCard : RegistrationUiState
}
