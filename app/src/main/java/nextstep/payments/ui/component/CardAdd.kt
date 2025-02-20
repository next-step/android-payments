package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toNewCard

@Composable
fun CardAdd(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(modifier.clickable { context.toNewCard() }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_card),
            tint = Color(0xFF575757),
            modifier = Modifier.size(20.dp),
        )
    }
}

@Preview
@Composable
private fun CardAddPreview() {
    PaymentsTheme {
        CardAdd()
    }
}