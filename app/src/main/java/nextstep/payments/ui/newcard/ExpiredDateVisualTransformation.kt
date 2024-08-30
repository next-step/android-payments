package nextstep.payments.ui.newcard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiredDateVisualTransformation : VisualTransformation {
    private val separator = " / "

    override fun filter(text: AnnotatedString): TransformedText {
        val formatted = formatExpirationDate(text.text)
        val offsetMapping = expiredDateOffsetMapping()

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    private fun formatExpirationDate(input: String): String {
        val expiredDate = input.filter { it.isDigit() }.take(4)
        return when {
            expiredDate.length > 2 -> expiredDate.substring(0, 2) + separator + expiredDate.substring(2)
            else -> expiredDate
        }
    }

    private fun expiredDateOffsetMapping() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int) = when {
            offset <= 2 -> offset
            else -> offset + separator.length
        }

        override fun transformedToOriginal(offset: Int) = when {
            offset <= 2 -> offset
            offset <= 2 + separator.length -> 2
            offset <= 7 -> offset - separator.length
            else -> 4
        }
    }
}