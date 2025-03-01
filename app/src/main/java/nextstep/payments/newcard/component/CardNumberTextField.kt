package nextstep.payments.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardNumberTextField(
    cardNumber: String,
    setCardNumber: (String) -> Unit
) {
    val placeholder = remember { "0000 - 0000 - 0000 - 0000" }
    OutlinedTextField(
        value = cardNumber,
        onValueChange = { number ->
            if (number.length <= 16) {
                setCardNumber(number.filter { it.isDigit() }.take(16))
            }
        },
        label = { Text(text = stringResource(R.string.card_number)) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = FourDigitDashVisualTransformation(),
        modifier = Modifier.fillMaxWidth(),
    )
}

private class FourDigitDashVisualTransformation : VisualTransformation {
    private class FourDigitDashOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + (offset / CARD_NUMBER_BLOCK_LENGTH)
                .coerceAtMost(CARD_NUMBER_BLOCK_COUNT - 1) * SEPARATOR.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset - (offset / CARD_NUMBER_BLOCK_LENGTH)
                .coerceAtMost(CARD_NUMBER_BLOCK_COUNT - 1) * SEPARATOR.length
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val numbers = text.text.chunked(CARD_NUMBER_BLOCK_LENGTH)

        val numbersDashed = numbers
            .joinToString(separator = SEPARATOR)
            .let {
                if (
                    numbers.isNotEmpty() &&
                    numbers.last().length == CARD_NUMBER_BLOCK_LENGTH &&
                    numbers.size < CARD_NUMBER_BLOCK_COUNT
                ) {
                    "$it - "
                } else {
                    it
                }
            }

        val offsetMapping = FourDigitDashOffsetMapping()
        return TransformedText(AnnotatedString(numbersDashed), offsetMapping)
    }

    companion object {
        private const val CARD_NUMBER_BLOCK_LENGTH = 4
        private const val CARD_NUMBER_BLOCK_COUNT = 4
        private const val SEPARATOR = " - "
    }
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFieldPreview() {
    val cardNumber = remember { mutableStateOf("") }

    CardNumberTextField(
        cardNumber = cardNumber.value,
        setCardNumber = {
            cardNumber.value = it
        }
    )
}
