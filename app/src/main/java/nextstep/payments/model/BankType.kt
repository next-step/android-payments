package nextstep.payments.model

import androidx.annotation.StringRes
import nextstep.payments.R

enum class BankType(@StringRes val titleRes: Int, val backgroundColor: Long, val logoUrl: String) {
    BC(
        R.string.bc_card,
        0xFFFF3348,
        "https://www.bccard.com/images/company/cyber/symbol_mark_banner.jpg",
    ),
    SHINHAN(
        R.string.shinhan_card,
        0XFF0046FF,
        "https://www.shinhancard.com/pconts/company/images/contents/shc_symbol_ci.png",
    ),
    KAKAOBANK(
        R.string.kakaobank,
        0xFFFFE300,
        "https://m.kakaobank.com/static/images/m/main/img-mweb-ico-3@2x.png",
    ),
    HYUNDAI(
        R.string.hyundai_card,
        0xFF000000,
        "https://www.hyundaicard.com/docfiles/resources/mo/images/com/badge/badge_Hyundaicard_large.png",
    ),
    WOORI(
        R.string.woori_card,
        0xFF0078B9,
        "https://logo-resources.thevc.kr/organizations/200x200/36cc202ff34f8a2645e41ab99e6f68bcb024e8533802fa9ee8eafb226e870b66_1673951858500452.jpg",
    ),
    LOTTE(
        R.string.lotte_card,
        0xFFDA291C,
        "https://img.khan.co.kr/news/2018/11/27/l_2018112701003250500253841.jpg",
    ),
    HANA(
        R.string.hana_card,
        0xFF008485,
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxdAIFIFlSUYSnjdqiTaIm2K8kdMME07UAGg&s",
    ),
    KB(
        R.string.kb_card,
        0xFF008485,
        "https://logo-resources.thevc.kr/organizations/200x200/9722fbb9c8b0ca1eff7d72a15be6eca7e09884a207e7d7707660faecd04d86ae_1646662511432117.jpg",
    ),
}
