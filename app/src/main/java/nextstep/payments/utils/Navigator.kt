package nextstep.payments.utils

import android.content.Context
import android.content.Intent
import nextstep.payments.NewCardActivity

fun Context.toNewCard() {
    startActivity(
        Intent(this, NewCardActivity::class.java)
    )
}