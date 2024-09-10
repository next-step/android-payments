package nextstep.payments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val id: Int = 0,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: CardCompany?
) : Parcelable