package nextstep.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = if (text.text.length > 16) text.text.substring(0..15) else text.text
        var transformedText = ""
        trimmedText.forEachIndexed { index, char ->
            transformedText += char
            if ((index + 1) % 4 == 0 && index != 15) transformedText += "-"
        }

        val cardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset < 4 -> offset
                    offset < 8 -> offset + 1
                    offset < 12 -> offset + 2
                    offset <= 16 -> offset + 3
                    else -> 19
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 9 -> offset - 1
                    offset <= 14 -> offset - 2
                    offset <= 19 -> offset - 3
                    else -> 16
                }
            }
        }

        return TransformedText(AnnotatedString(transformedText), cardOffsetTranslator)
    }
}