package nextstep.payments.utils


fun String.formatAndMaskingCardNumber() : String {
    val formattedNumber = this.chunked(4).joinToString("-")
    val parts = formattedNumber.split("-")
    return parts.mapIndexed { index, part ->
        if (index >= parts.size - 2) "*".repeat(part.length)
        else part
    }.joinToString("-")
}

fun String.formatExpiredDate() : String {
    return "${this.take(2)} / ${this.drop(2)}"
}