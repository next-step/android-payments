package nextstep.payments.ui.utils

fun String.toTransformedCardNumber(
    maxLength: Int,
    maskStartIndex: Int,
    maskChar: Char,
    groupSize: Int,
    separator: String
): String {
    val sanitizedText = this.filter { it.isDigit() }.take(maxLength)
    return buildString {
        sanitizedText.forEachIndexed { index, character ->
            append(if (index >= maskStartIndex) maskChar else character)
            if ((index + 1) % groupSize == 0 && index < sanitizedText.length - 1) {
                append(separator)
            }
        }
    }
}

fun String.toFormattedExpirationDate(maxLength: Int, separator: String): String {
    val trimmed = this.filter { it.isDigit() }.take(maxLength)
    return buildString {
        trimmed.forEachIndexed { index, char ->
            if (index == 2) {
                append(separator)
            }
            append(char)
        }
    }
}
