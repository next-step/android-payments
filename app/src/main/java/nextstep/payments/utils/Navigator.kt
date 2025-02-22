package nextstep.payments.utils

import android.content.Context
import android.content.Intent
import nextstep.payments.CardAddActivity
import nextstep.payments.CardListActivity

fun Context.toCardAdd() {
    startActivity(
        Intent(this, CardAddActivity::class.java)
    )
}
fun Context.toCardList() {
    startActivity(
        Intent(this, CardListActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    )
}