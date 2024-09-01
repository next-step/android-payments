package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCompanySelectRow(
    cardCompanies: List<CardCompany>,
    onClickCardCompany: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 36.dp, horizontal = 48.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        cardCompanies.forEach { cardCompany ->
            Column(
                modifier = Modifier.clickable { onClickCardCompany(cardCompany) },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = cardCompany.iconResource),
                    contentDescription = "${cardCompany.companyName}Image",
                    modifier = Modifier.size(37.dp),
                )

                Text(
                    text = cardCompany.companyName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectRowPreview() {
    PaymentsTheme {
        CardCompanySelectRow(cardCompanies = CardCompany.entries, onClickCardCompany = {})
    }
}

