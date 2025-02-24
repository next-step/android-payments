package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.designsystem.theme.TextColor


@Composable
fun EmptyCardContainer(
    onRouteToNewCard: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.content_add_card),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor,
            modifier = Modifier.padding(vertical = 32.dp)
        )
        AddCardButton(onAddClick = onRouteToNewCard)
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardContainerPreview() {
    PaymentsTheme {
        EmptyCardContainer(onRouteToNewCard = {})
    }
}