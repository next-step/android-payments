package nextstep.payments.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import kotlinx.coroutines.launch
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyModalBottomSheet(
    bankTypeModelList: List<BankTypeModel>,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onCardCompanySelected: (BankTypeModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        properties = ModalBottomSheetProperties(
            securePolicy = SecureFlagPolicy.Inherit,
            isFocusable = true,
            shouldDismissOnBackPress = false
        ),
        onDismissRequest = { onDismissRequest() }
    ) {
        CardCompanyContents(
            modifier = Modifier.navigationBarsPadding(),
            bankTypeModelList = bankTypeModelList,
            onCardCompanySelected = { company ->
                onCardCompanySelected(company)
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    onDismissRequest()
                }
            }
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun CardCompanyContents(
    bankTypeModelList: List<BankTypeModel>,
    onCardCompanySelected: (BankTypeModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 36.dp, horizontal = 40.dp),
        maxItemsInEachRow = 4,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        bankTypeModelList.forEach { company ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onCardCompanySelected(company)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                company.imageRes?.let {
                    Image(
                        modifier = Modifier.size(37.dp),
                        painter = painterResource(id = it),
                        contentDescription = company.companyName
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 9.dp),
                    text = company.companyName,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun 카드회사_아이템_프리뷰() {
    PaymentsTheme {
        CardCompanyContents(
            bankTypeModelList = BankTypeModel.getCardBrandList(),
            onCardCompanySelected = {}
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun 카드회사_바텀시트_인터렉션용_프리뷰() {
    var showBottomSheet by remember { mutableStateOf(false) }
    PaymentsTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showBottomSheet = true }) {
                Text(text = "Show Bottom Sheet")
            }
        }

        if (showBottomSheet) {
            CardCompanyModalBottomSheet(
                sheetState = rememberModalBottomSheetState(),
                bankTypeModelList = BankTypeModel.entries,
                onDismissRequest = { showBottomSheet = false },
                onCardCompanySelected = {}
            )
        }
    }
}
