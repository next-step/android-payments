package nextstep.payments.model.card

import nextstep.payments.model.bank.BankType
import java.time.YearMonth

internal data class CreditCard(
    val cardNumbers: List<CardNumber>,
    val expiredDate: YearMonth,
    val password: String,
    val ownerName: String,
    val bankType: BankType,
) {
    init {
        require(cardNumbers.size == 4) { "카드 넘버는 4개여야 합니다" }
        require(cardNumbers.all { it.number.length == 4 }) { "모든 카드 넘버는 4자리여야 합니다" }
        require(isExpiredDateValid(expiredDate)) { "카드 유효기간이 현재 또는 미래여야 합니다" }
        require(password.length == 4) { "패스워드는 4자리여야 합니다" }
        require(ownerName.isNotBlank()) { "소유자 이름은 비어 있을 수 없습니다" }
    }

    private fun isExpiredDateValid(expiredDate: YearMonth): Boolean {
        val now = YearMonth.now()
        return expiredDate >= now
    }
}

data class CardNumber(val number: String) {
    init {
        require(number.length == 4) { "카드 넘버의 길이가 4이어야 하지만 그렇지 않습니다" }
    }
}