package nextstep.payments.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CardAdd(
    onCardAddClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable(
                onClick = onCardAddClicked,
                indication = rememberRipple(bounded = true),
                interactionSource = MutableInteractionSource()
            ),
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.card_screen_card_add),
        )
    }
}

@Preview
@Composable
fun CardAddPreview() {
    PaymentsTheme {
        CardAdd(onCardAddClicked = {})
    }
}
