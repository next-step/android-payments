package nextstep.payments.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import nextstep.payments.R

enum class BankType(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val backgroundColor: Long,
) {
    BC(
        R.string.bc_card,
        R.drawable.ic_bc,
        0xFFFF3348,
    ),
    SHINHAN(
        R.string.shinhan_card,
        R.drawable.ic_shinhan,
        0XFF0046FF,
    ),
    KAKAOBANK(
        R.string.kakaobank,
        R.drawable.ic_kakaobank,
        0xFFFFE300,
    ),
    HYUNDAI(
        R.string.hyundai_card,
        R.drawable.ic_hyundai,
        0xFF000000,
    ),
    WOORI(
        R.string.woori_card,
        R.drawable.ic_woori,
        0xFF0078B9,
    ),
    LOTTE(
        R.string.lotte_card,
        R.drawable.ic_lotte,
        0xFFDA291C,
    ),
    HANA(
        R.string.hana_card,
        R.drawable.ic_hana,
        0xFF008485,
    ),
    KB(
        R.string.kb_card,
        R.drawable.ic_kb,
        0xFF695F54,
    ),
}
