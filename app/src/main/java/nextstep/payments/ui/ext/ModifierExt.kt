package nextstep.payments.ui.ext

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


fun Modifier.cardDefaultSize(): Modifier =
    this.then(size(208.dp, 124.dp))
