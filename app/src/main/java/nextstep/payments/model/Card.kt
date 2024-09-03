package nextstep.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Card(
    val id: Int?,
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val cardPassword: String,
    val bankType: BankType
) : Parcelable
