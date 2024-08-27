package nextstep.payments.component.textfield

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/ui/text/input/VisualTransformation 참고
 */
class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % 4 == 3 && i != 15) out += " - "
        }
        val cardNumberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + 3
                if (offset <= 11) return offset + 6
                if (offset <= 16) return offset + 9
                return 25
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 11) return offset - 3
                if (offset <= 18) return offset - 6
                if (offset <= 25) return offset - 9
                return 16
            }
        }

        return TransformedText(
            AnnotatedString(out),
            cardNumberOffsetTranslator
        )
    }
}

class ExpiredDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % 2 == 1 && i != 3) out += " / "
        }
        val expiredDateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 4) return offset + 3
                return 6
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 7) return offset - 3
                return 4
            }
        }

        return TransformedText(
            AnnotatedString(out),
            expiredDateOffsetTranslator
        )
    }
}