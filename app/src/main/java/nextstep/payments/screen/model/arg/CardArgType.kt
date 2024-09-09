package nextstep.payments.screen.model.arg

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import nextstep.payments.data.model.CreditCard

sealed class CardArgType : Parcelable {
    @Parcelize
    data object AddCardArg : CardArgType()
    @Parcelize
    data class EditCardArg(val creditCard: CreditCard) : CardArgType()

    val creditCardToEdit : CreditCard? by lazy {
        when(this){
            is EditCardArg -> this.creditCard
            else -> null
        }
    }

    companion object {
        const val MANAGE_CARD_TYPE_ARG = "manageCardTypeArg"
    }
}
