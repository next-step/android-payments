package nextstep.payments.utils

fun String.maskCardNumber() : String {
    val parts = this.split("-")
    return parts.mapIndexed { index, part ->
        if (index >= parts.size - 2) "*".repeat(part.length)
        else part
    }.joinToString("-")
}