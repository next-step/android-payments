package nextstep.payments.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCompanyFlowRow(
    onCompanyClick: (CardCompany) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        CardCompany.entries.forEach { cardCompany ->
            CardCompanyItem(
                cardCompany = cardCompany,
                onClick = { onCompanyClick(cardCompany) }
            )
        }
    }
}

@Composable
private fun CardCompanyItem(
    cardCompany: CardCompany,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardCompanyRes = remember(key1 = cardCompany) { cardCompany.toResource() } ?: return

    Column(
        modifier = modifier.width(80.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(37.dp)
                .clip(CircleShape),
            painter = painterResource(id = cardCompanyRes.iconRes),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = cardCompanyRes.stringRes),
            color = Color(0xFF525252)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanyItemPreview() {
    PaymentsTheme {
        CardCompanyItem(
            cardCompany = CardCompany.BC,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanyFlowRowPreview() {
    PaymentsTheme {
        CardCompanyFlowRow(
            onCompanyClick = {}
        )
    }
}