package nextstep.payments.ui.newcard.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.data.model.Bank
import nextstep.payments.ui.theme.BCMainColor
import nextstep.payments.ui.theme.HanaMainColor
import nextstep.payments.ui.theme.HyundaiMainColor
import nextstep.payments.ui.theme.KBMainColor
import nextstep.payments.ui.theme.KakaoMainColor
import nextstep.payments.ui.theme.LotteMainColor
import nextstep.payments.ui.theme.ShinhanMainColor
import nextstep.payments.ui.theme.WooriMainColor

enum class BankUI(
    private val bank: Bank,
    val bankName: String,
    val color: Color,
    @StringRes val imageResId: Int
) {
    BC(Bank.BC, "BC카드", BCMainColor, R.drawable.bc_card),
    SHINHAN(Bank.SHINHAN, "신한카드", ShinhanMainColor, R.drawable.shinhan_card),
    KAKAO(Bank.KAKAO, "카카오뱅크", KakaoMainColor, R.drawable.kakao_bank),
    HYUNDAI(Bank.HYUNDAI, "현대카드", HyundaiMainColor, R.drawable.hyundai_card),
    WOORI(Bank.WOORI, "우리카드", WooriMainColor, R.drawable.woori_card),
    LOTTE(Bank.LOTTE, "롯데카드", LotteMainColor, R.drawable.lotte_card),
    HANA(Bank.HANA, "하나카드", HanaMainColor, R.drawable.hana_card),
    KB(Bank.KB, "국민카드", KBMainColor, R.drawable.kb_card);

    fun toBank(): Bank {
        return bank
    }

    companion object {
        fun fromBank(bank: Bank): BankUI? {
            return entries.find { it.name == bank.name }
        }
    }
}
