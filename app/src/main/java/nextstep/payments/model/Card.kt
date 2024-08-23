package nextstep.payments.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

data class Card(
    val id: Long = 0,
    val brand: Brand,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)

enum class Brand {
    NONE,
    BC,
    SHINHAN,
    KAKAO_BANK,
    HYUNDAI,
    WOORI,
    LOTTE,
    HANA,
    KB,
}

@Composable
fun Brand.toColor(): Color =
    when (this) {
        Brand.NONE -> Color(0xFF333333)
        Brand.BC -> Color(0xFFF04651)
        Brand.SHINHAN -> Color(0xFF0248f8)
        Brand.KAKAO_BANK -> Color(0xFFfce237)
        Brand.HYUNDAI -> Color.Black
        Brand.WOORI -> Color(0xFF2870bd)
        Brand.LOTTE -> Color(0xFFd83528)
        Brand.HANA -> Color(0xFF348685)
        Brand.KB -> Color(0xFF695F54)
    }

@Composable
fun Brand.toName(): String =
    stringResource(
        id =
            when (this) {
                Brand.NONE -> R.string.brand_none
                Brand.BC -> R.string.brand_bc
                Brand.SHINHAN -> R.string.brand_shinhan
                Brand.KAKAO_BANK -> R.string.brand_kakao_bank
                Brand.HYUNDAI -> R.string.brand_hyundai
                Brand.WOORI -> R.string.brand_woori
                Brand.LOTTE -> R.string.brand_lotte
                Brand.HANA -> R.string.brand_hana
                Brand.KB -> R.string.brand_kb
            },
    )

@Composable
fun Brand.toIcon() =
    when (this) {
        Brand.NONE -> null
        Brand.BC -> R.drawable.bc_card
        Brand.SHINHAN -> R.drawable.shinhan_card
        Brand.KAKAO_BANK -> R.drawable.kb_card
        Brand.HYUNDAI -> R.drawable.hyundai_card
        Brand.WOORI -> R.drawable.woori_card
        Brand.LOTTE -> R.drawable.lotte_card
        Brand.HANA -> R.drawable.hana_card
        Brand.KB -> R.drawable.kb_card
    }?.let { painterResource(id = it) }
