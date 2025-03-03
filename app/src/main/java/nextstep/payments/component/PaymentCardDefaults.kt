package nextstep.payments.component

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object PaymentCardDefaults {

    private val CardWidth = 208.dp
    private val CardHeight = 124.dp

    val CardSize = DpSize(CardWidth, CardHeight)

    const val UNMASKED_NUMBER_LENGTH = 8
    const val CHUNKED_NUMBER_LENGTH = 4
    const val DUE_DATE_LENGTH = 4
    const val CHUNKED_DUE_DATE_LENGTH = 2

}