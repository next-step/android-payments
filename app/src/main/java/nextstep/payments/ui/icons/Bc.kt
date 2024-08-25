package nextstep.payments.ui.icons

import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIconPack.Bc: ImageVector
    get() {
        if (_bc != null) {
            return _bc!!
        }
        _bc = Builder(
            name = "Bc", defaultWidth = 37.0.dp, defaultHeight = 37.0.dp, viewportWidth =
            37.0f, viewportHeight = 37.0f
        ).apply {
            path(
                fill = null, stroke = null, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(37.0f, 18.5001f)
                curveTo(37.0f, 28.7173f, 28.7173f, 37.0001f, 18.5f, 37.0001f)
                curveTo(8.2827f, 37.0001f, 0.0f, 28.7173f, 0.0f, 18.5001f)
                curveTo(0.0f, 8.2828f, 8.2827f, 1.0E-4f, 18.5f, 1.0E-4f)
                curveTo(28.7173f, 1.0E-4f, 37.0f, 8.2828f, 37.0f, 18.5001f)
                close()
            }
        }
            .build()
        return _bc!!
    }

private var _bc: ImageVector? = null
