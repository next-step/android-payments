package nextstep.payments.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.common.component.EmptyCard

@Composable
fun CardsEmptyScreen(
    moveToAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.add_new_card),
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.5.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        EmptyCard(
            modifier = Modifier.clickable { moveToAddCard() }
        )
    }
}
