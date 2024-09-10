import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelector(
    cardCompanies: List<CardCompany>,
    onCompanySelected: (CardCompany) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    LaunchedEffect(Unit) {
        scope.launch {
            bottomSheetState.show()
        }
    }

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest,
    ) {
        BankSelectRow(
            cardCompanies = cardCompanies,
            onCompanySelected = onCompanySelected,
            bottomSheetState = bottomSheetState,
            scope = scope
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            onDismissRequest()
        }
    }
}

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BankSelectRow(
    cardCompanies: List<CardCompany>,
    onCompanySelected: (CardCompany) -> Unit,
    bottomSheetState: SheetState,
    scope: CoroutineScope
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        FlowRow(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 36.dp, bottom = 106.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            maxItemsInEachRow = COLUMN_COUNT
        ) {
            cardCompanies.take(ROW_COUNT * COLUMN_COUNT).forEach { company ->
                Column(
                    modifier = Modifier
                        .padding(bottom = 23.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onCompanySelected(company)
                            scope.launch { bottomSheetState.hide() }
                        }
                ) {
                    Image(
                        painter = painterResource(id = company.logo),
                        contentDescription = company.name,
                        modifier = Modifier
                            .size(48.dp)
                            .align(CenterHorizontally)
                    )
                    Text(
                        text = company.name,
                        fontSize = 16.sp,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BankSelectRowPreview() {
    PaymentsTheme {
        BankSelectRow(
            cardCompanies = listOf(
                CardCompany(logo = R.drawable.bc, name = "BC카드"),
                CardCompany(logo = R.drawable.shinhan, name = "신한카드"),
                CardCompany(logo = R.drawable.kakaobank, name = "카카오뱅크"),
                CardCompany(logo = R.drawable.hyundai, name = "현대카드"),
                CardCompany(logo = R.drawable.woori, name = "우리카드"),
                CardCompany(logo = R.drawable.lotte, name = "롯데카드"),
                CardCompany(logo = R.drawable.hana, name = "하나카드"),
                CardCompany(logo = R.drawable.kb, name = "국민카드")
            ),
            onCompanySelected = { company -> println("Selected: $company") },
            bottomSheetState = rememberModalBottomSheetState(
                confirmValueChange = { false }
            ),
            scope = rememberCoroutineScope()
        )
    }
}
