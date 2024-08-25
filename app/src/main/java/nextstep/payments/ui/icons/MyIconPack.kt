package nextstep.payments.ui.icons

import androidx.compose.ui.graphics.vector.ImageVector

object MyIconPack

private var __AllIcons: List<ImageVector>? = null

val MyIconPack.AllIcons: List<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(Kb, Hana, Hyundai, Kakao, Bc, Woori, Lotte, Shinhan)
        return __AllIcons!!
    }
