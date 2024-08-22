package nextstep.payments.ui.component.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.OffsetMapping
import nextstep.payments.ui.utils.toFormattedExpirationDate

class ExpirationDateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = text.text.toFormattedExpirationDate(
            maxLength = MAX_LENGTH,
            separator = SEPARATOR
        )

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset in 3..4 -> offset + SEPARATOR.length
                    else -> formatted.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset in 3 + SEPARATOR.length..formatted.length -> offset - SEPARATOR.length
                    else -> text.text.filter { it.isDigit() }.take(MAX_LENGTH).length
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    companion object {
        private const val MAX_LENGTH = 4
        private const val SEPARATOR = " / "
    }
}
