package nextstep.payments.extension

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getExtra(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key)
    }
}
