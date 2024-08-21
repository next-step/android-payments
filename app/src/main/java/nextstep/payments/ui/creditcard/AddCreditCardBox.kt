package nextstep.payments.ui.creditcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun AddCreditCardBox(
    width: Dp = 208.dp,
    height: Dp = 124.dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .then(Modifier.size(width = width, height = height))
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Color(0xFFE5E5E5))
                .clickable(onClick = onClick),
    ) {
        Text(
            text = "+",
            fontSize = 32.sp,
            modifier = Modifier.size(width = 20.dp, height = 40.dp),
        )
    }
}
