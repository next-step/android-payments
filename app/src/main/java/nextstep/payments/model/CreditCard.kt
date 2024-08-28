package nextstep.payments.model

import java.time.YearMonth

data class CreditCard(
    val cardNumbers: List<CardNumber>,
    val expiredDate: YearMonth,
    val password: String,
    val ownerName: String,
) {
    init {
        require(cardNumbers.size == 4) { "카드 넘버는 4개여야 합니다" }
    }
}

data class CardNumber(val number: String) {
    init {
        require(number.length == 4) { "카드 넘버의 길이가 4이어야 하지만 그렇지 않습니다" }
    }
}