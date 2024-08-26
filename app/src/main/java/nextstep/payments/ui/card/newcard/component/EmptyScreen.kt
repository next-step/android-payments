package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.card.list.component.NewCard

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    onShowNewCard: () -> Unit,
) {
    Column(modifier = modifier.padding(top = 32.dp)) {
        Text(
            text = stringResource(id = R.string.register_new_card),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        NewCard(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(208.dp)
                .height(124.dp),
            onClick = onShowNewCard,
        )
    }
}
