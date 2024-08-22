package nextstep.payments.ui.component.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.payments.ui.utils.toTransformedCardNumber

class CreditCardVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.toTransformedCardNumber(
            maxLength = MAX_LENGTH,
            maskStartIndex = MASK_START_INDEX,
            maskChar = MASK_CHAR.first(),
            groupSize = GROUP_SIZE,
            separator = SEPARATOR
        )

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val sanitizedText = text.text.filter { it.isDigit() }.take(MAX_LENGTH)
                return if (offset >= sanitizedText.length) {
                    transformedText.length
                } else {
                    val numberOfSeparators = offset / GROUP_SIZE
                    offset + numberOfSeparators * SEPARATOR.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                val sanitizedText = text.text.filter { it.isDigit() }.take(MAX_LENGTH)
                return if (offset >= transformedText.length) {
                    sanitizedText.length
                } else {
                    val numberOfSeparators = offset / (GROUP_SIZE + SEPARATOR.length)
                    offset - numberOfSeparators * SEPARATOR.length
                }
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

    companion object {
        private const val MAX_LENGTH = 16
        private const val GROUP_SIZE = 4
        private const val SEPARATOR: String = " - "
        private const val MASK_CHAR: String = "*"
        private const val MASK_START_INDEX = GROUP_SIZE * 2
    }
}
