package nextstep.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val numbers: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: CardCompany,
): Parcelable
