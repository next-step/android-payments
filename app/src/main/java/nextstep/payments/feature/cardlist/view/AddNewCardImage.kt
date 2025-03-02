package nextstep.payments.feature.cardlist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddNewCardImage(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { onAddClick() }
            .testTag("AddNewCardImage")
    ) {
        Text(
            text = "+",
            fontSize = 34.sp,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
