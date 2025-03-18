package nextstep.payments.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R

enum class CardCompany(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    val color: Color
) {
    BC(R.string.bc_name, R.drawable.img_bc, Color(0xFFF04651)),
    SHINHAN(R.string.shinhan_name, R.drawable.img_shinhan, Color(0xFF0554F2)),
    KAKAOBANK(R.string.kakao_name, R.drawable.img_kakaobank, Color(0xFFE3CE10)),
    HYUNDAI(R.string.hyundai_name, R.drawable.img_hyundai, Color(0xFF000000)),
    WOORI(R.string.woori_name,R.drawable.img_woori, Color(0xFF0477BF)),
    LOTTE(R.string.lotte_name, R.drawable.img_lotte, Color(0xFFF21D2F)),
    HANA(R.string.hana_name, R.drawable.img_hana, Color(0xFF038C7F)),
    KB(R.string.kb_name, R.drawable.img_kb, Color(0xFF696156)),
}
