package nextstep.payments.screen.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R

enum class BankTypeUiModel(
    @StringRes val nameStringRes: Int,
    @DrawableRes val imageDrawableRes: Int,
    val color : Color,
) {
    BC(
        nameStringRes = R.string.bank_type_bc,
        imageDrawableRes = R.drawable.bc,
        color = Color(0xFFF04651)
    ),
    SHINHAN(
        nameStringRes = R.string.bank_type_shinhan,
        imageDrawableRes = R.drawable.shnhan,
        color = Color(0xFF0046ff)
    ),
    KAKAO(
        nameStringRes = R.string.bank_type_kakao,
        imageDrawableRes = R.drawable.kakako,
        color = Color(0xFFffe600)
    ),
    HYUNDAI(
        nameStringRes = R.string.bank_type_hyundai,
        imageDrawableRes = R.drawable.hyundai,
        color = Color.Black
    ),
    WOORI(
        nameStringRes = R.string.bank_type_woori,
        imageDrawableRes = R.drawable.woori,
        color = Color(0xFF027bc8)
    ),
    LOTTE(
        nameStringRes = R.string.bank_type_lotte,
        imageDrawableRes = R.drawable.lotte,
        color = Color(0xFFed1c23)
    ),
    HANA(
        nameStringRes = R.string.bank_type_hana,
        imageDrawableRes = R.drawable.hana,
        color = Color(0xFF009490)
    ),
    KB(
        nameStringRes = R.string.bank_type_kookmin,
        imageDrawableRes = R.drawable.kookmin,
        color = Color(0xFF695F54)
    )
}


