package nextstep.payments.ui.component.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R
import nextstep.payments.model.bank.BankType

enum class CardBankInformation(
    val bankType: BankType,
    @DrawableRes val iconRes: Int,
    @StringRes val nameRes: Int,
) {
    Bc(BankType.BC, R.drawable.bc, R.string.bc_name),
    Shinhan(BankType.SHINHAN, R.drawable.shinhan, R.string.shinhan_name),
    Kakao(BankType.KAKAO, R.drawable.kakao, R.string.kakao_name),
    Hyundai(BankType.HYUNDAI, R.drawable.hyundai, R.string.hyundai_name),
    Woori(BankType.WOORI, R.drawable.woori, R.string.woori_name),
    Lotte(BankType.LOTTE, R.drawable.lotte, R.string.lotte_name),
    Hana(BankType.HANA, R.drawable.hana, R.string.hana_name),
    Kb(BankType.KB, R.drawable.kb, R.string.kb_name),
    None(BankType.NONE, R.drawable.none, R.string.none_name);

    companion object {
        private val map = CardBankInformation.entries.associateBy(CardBankInformation::bankType)

        fun from(bankType: BankType): CardBankInformation {
            return map.getOrElse(bankType) { error("적절하지 않은 BankType: $bankType") }
        }
    }
}
