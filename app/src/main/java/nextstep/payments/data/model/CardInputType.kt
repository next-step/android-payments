package nextstep.payments.data.model

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.payments.utils.CardNumberVisualTransformation
import nextstep.payments.utils.ExpirationDateVisualTransformation

enum class CardInputType(
    val visualTransformation: VisualTransformation,
    val keyboardOptions: KeyboardOptions,
    val maxLength: Int,
) {
    CardNumber(
        visualTransformation = CardNumberVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLength = 16,
    ) {
        override fun maskingText(value: String): String {
            val maskingText = value.take(8) + "*".repeat((value.length - 8).coerceAtLeast(0))
            var output = ""
            maskingText.forEachIndexed { index, c ->
                output += c + if (index % 4 == 3) "-" else ""
            }
            return output.dropLastWhile { it == '-' }
        }
    },
    OwnerName(
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLength = 20,
    ) {
        override fun maskingText(value: String): String {
            return value
        }
    },
    ExpiredDate(
        visualTransformation = ExpirationDateVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLength = 4,
    ) {
        override fun maskingText(value: String): String {
            return if (value.length > 2) "${value.take(2)}/${value.takeLast(2)}" else value
        }
    },
    Password(
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        maxLength = 4,
    ) {
        override fun maskingText(value: String): String {
            return value
        }
    }, ;

    abstract fun maskingText(value: String): String
}