package nextstep.payments.ui.icons

import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIconPack.Lotte: ImageVector
    get() {
        if (_lotte != null) {
            return _lotte!!
        }
        _lotte = Builder(
            name = "Lotte", defaultWidth = 37.0.dp, defaultHeight = 37.0.dp,
            viewportWidth = 37.0f, viewportHeight = 37.0f
        ).apply {
            path(
                fill = null, stroke = null, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(18.5f, 18.5f)
                moveToRelative(-18.5f, 0.0f)
                arcToRelative(18.5f, 18.5f, 0.0f, true, true, 37.0f, 0.0f)
                arcToRelative(18.5f, 18.5f, 0.0f, true, true, -37.0f, 0.0f)
            }
        }
            .build()
        return _lotte!!
    }

private var _lotte: ImageVector? = null
