package nextstep.payments.utils

import android.content.Context
import android.content.Intent
import nextstep.payments.CardAddModifyActivity
import nextstep.payments.CardListActivity

fun Context.toCardAdd() {
    startActivity(
        Intent(this, CardAddModifyActivity::class.java)
    )
}

fun Context.toCardModify(cardId: Long) {
    startActivity(
        Intent(this, CardAddModifyActivity::class.java)
            .apply { putExtra(PARAM_CARD_ID, cardId) }
    )
}

fun Context.toCardList() {
    startActivity(
        Intent(this, CardListActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    )
}

const val PARAM_CARD_ID = "PARAM_CARD_ID"