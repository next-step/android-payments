package nextstep.payments.ui.card.registration.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.CardCompany
import nextstep.payments.data.cardCompanies
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {

        cardCompanies.forEach { cardCompany ->
            CardSelector(
                cardCompany = cardCompany,
            )
        }
    }
}

@Composable
fun CardSelector(
    cardCompany: CardCompany,
) {
    Column(
        modifier = Modifier.size(80.dp),
    ){
        Image(
            painter = painterResource(id = cardCompany.logo),
            contentDescription = stringResource(id = cardCompany.name),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .weight(1f),
        )

        Text(
            text = stringResource(id = cardCompany.name),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Preview
@Composable
private fun BankSelectRowPreview() {
    PaymentsTheme {
        BankSelectRow()
    }
}


