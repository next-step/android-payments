package nextstep.payments.component.visiualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return cardNumberFilter(text)
    }

    private fun cardNumberFilter(text: AnnotatedString): TransformedText {
        val builder = StringBuilder()
        for (i in text.indices) {
            builder.append(text[i])
            if (i % 4 == 3 && i != text.lastIndex) {
                builder.append("-")
            }
        }
        val output = builder.toString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (output.isEmpty() || offset == 0) return 0
                val numberOfDashes = (offset - 1) / 4
                return offset + numberOfDashes
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (output.isEmpty() || offset == 0) return 0
                val numberOfDashes = offset / 5
                return offset - numberOfDashes
            }
        }

        return TransformedText(AnnotatedString(output), offsetMapping)
    }
}
