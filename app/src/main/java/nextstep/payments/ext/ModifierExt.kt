package nextstep.payments.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics


fun Modifier.setContentDescription(desc: String) : Modifier {
    return semantics { contentDescription = desc }
}