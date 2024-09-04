package nextstep.payments.ui.component.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import nextstep.payments.R
import android.graphics.Color as AndroidColor

fun getDominantColorFromDrawable(context: Context, @DrawableRes drawableRes: Int): Int {
    val drawable =
        ContextCompat.getDrawable(context, drawableRes) ?: return AndroidColor.TRANSPARENT
    val bitmap = drawableToBitmap(drawable)
    val dominantColorInt = getDominantColor(bitmap)
    val colorWithAlpha = (dominantColorInt and 0x00FFFFFF) or (0xFF shl 24) // 알파값 추가

    return colorWithAlpha
}

private fun drawableToBitmap(drawable: Drawable): Bitmap {
    if (drawable is BitmapDrawable) return drawable.bitmap

    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

private fun getDominantColor(bitmap: Bitmap): Int {
    val colorCountMap = mutableMapOf<Int, Int>()

    for (x in 0 until bitmap.width) {
        for (y in 0 until bitmap.height) {
            val pixel = bitmap.getPixel(x, y)

            val color = pixel and 0x00FFFFF // 알파 값을 제외한 RGB 값
            if (color == 0) continue
            colorCountMap[color] = colorCountMap.getOrDefault(color, 0) + 1
        }
    }

    return colorCountMap.maxByOrNull { it.value }?.key ?: AndroidColor.TRANSPARENT
}

@Composable
@Preview
private fun ColorExtractionPreview() {
    val drawableRes = R.drawable.ic_android_24dp

    val dominantColor = getDominantColorFromDrawable(
        context = LocalContext.current,
        drawableRes = drawableRes
    )

    Surface(
        modifier = Modifier.size(500.dp),
        color = Color(dominantColor)
    ) {
        Text(text = dominantColor.toString())
        Image(imageVector = ImageVector.vectorResource(id = drawableRes), contentDescription = null)
    }
}
