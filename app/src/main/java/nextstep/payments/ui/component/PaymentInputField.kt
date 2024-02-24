package nextstep.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentInputField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = text.take(maxLength),
        onValueChange = { onTextChange(it) },
        modifier = modifier.padding(top = 20.dp),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = hint, color = Color.LightGray)
        },
        singleLine = true,
        visualTransformation = visualTransformation
    )
}

class CreditCardVisualTransformation(
    private val cardNumberSeparator: String = " – "
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return creditCardFilter(text, cardNumberSeparator)
    }
}

class ExpirationDateVisualTransformation(
    private val dateSeparator: String = " / "
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return expirationDateFilter(text, dateSeparator)
    }
}

fun expirationDateFilter(
    text: AnnotatedString,
    dateSeparator: String = " / "
): TransformedText {
    val trimmed = text.text.take(4)
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 1) out += dateSeparator
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 1 + dateSeparator.length) return offset + dateSeparator.length
            return 4 + dateSeparator.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 2 + dateSeparator.length) return offset - dateSeparator.length
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

fun creditCardFilter(
    text: AnnotatedString,
    cardNumberSeparator: String = " – "
): TransformedText {

    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += cardNumberSeparator
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + cardNumberSeparator.length
            if (offset <= 11) return offset + cardNumberSeparator.length * 2
            if (offset <= 16) return offset + cardNumberSeparator.length * 3
            return 16 + cardNumberSeparator.length * 3
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - cardNumberSeparator.length
            if (offset <= 14) return offset - cardNumberSeparator.length * 2
            if (offset <= 19) return offset - cardNumberSeparator.length * 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

@Preview(showBackground = true)
@Composable
fun PaymentInputFieldPreview() {
    PaymentInputField(
        text = "test",
        onTextChange = {},
        label = "label",
        hint = "hint"
    )
}
