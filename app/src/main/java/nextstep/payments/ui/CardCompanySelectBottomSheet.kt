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
import nextstep.payments.R
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.NewCardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelectBottomSheet(
    viewModel: NewCardViewModel,
    onCompanySelected: (CardCompany) -> Unit,
    onDismissRequest: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = showBottomSheet) {
        if (showBottomSheet) {
            bottomSheetState.hide()
        }
    }

    if (!showBottomSheet) {
        CardCompanySelectBottomSheetContent(
            bottomSheetState = bottomSheetState,
            cardCompanies = viewModel.cardCompanies,
            onCompanySelected = { company ->
                showBottomSheet = true
                onCompanySelected(company)
            },
            onDismissRequest = {
                showBottomSheet = true
                onDismissRequest()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelectBottomSheetContent(
    bottomSheetState: SheetState,
    cardCompanies: List<CardCompany>,
    onCompanySelected: (CardCompany) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest,
        properties = ModalBottomSheetDefaults.properties(
            shouldDismissOnBackPress = false,
        ),
    ) {
        BankSelectRow(
            cardCompanies = cardCompanies,
            onCompanySelected = onCompanySelected,
        )
    }
}

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    cardCompanies: List<CardCompany>,
    onCompanySelected: (CardCompany) -> Unit,
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
        )
    }
}
