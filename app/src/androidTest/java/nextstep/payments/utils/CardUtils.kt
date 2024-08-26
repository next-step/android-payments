package nextstep.payments.utils

fun String.chunkedCardNumber() = chunked(4).joinToString(" ")
