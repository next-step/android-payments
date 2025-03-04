package nextstep.payments.data.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import nextstep.payments.R

enum class BankType(
    @StringRes val nameResId: Int,
    @DrawableRes val iconResId: Int,
    @ColorRes val colorResId: Int,
) {
    BC(R.string.bc_card, R.drawable.ic_bc_bank, R.color.bc),
    SINHAN(R.string.sinhan_card, R.drawable.ic_sinhan_bank, R.color.sinhan),
    KAKAO(R.string.kakao_bank, R.drawable.ic_kakao_bank, R.color.kakao),
    HYONDAI(R.string.hyondai_card, R.drawable.ic_hyondai_bank, R.color.hyondai),
    WOORI(R.string.woori_card, R.drawable.ic_woori_bank, R.color.woori),
    LOTTE(R.string.lotte_card, R.drawable.ic_lotte_bank, R.color.lotte),
    HANA(R.string.hana_card, R.drawable.ic_hana_bank, R.color.hana),
    KOOKMI(R.string.kookmin_card, R.drawable.ic_kookmin_bank, R.color.kookmin),
    NOT_SELECTED(ResourcesCompat.ID_NULL, ResourcesCompat.ID_NULL, R.color.bank_not_selected);

    companion object {
        fun getSelectableTypes() = entries.filter { it != NOT_SELECTED }
    }
}
