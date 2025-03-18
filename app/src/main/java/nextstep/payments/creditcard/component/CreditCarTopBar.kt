package nextstep.payments.creditcard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.creditcard.model.CreditCardUiState
import nextstep.payments.model.CreditCardType.CardInfo
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardTopBar(uiState: CreditCardUiState, onNavigateToNewCard: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text("Payments", fontSize = 22.sp) },
        actions = {
            when (uiState) {
                CreditCardUiState.Empty, CreditCardUiState.Loading, is CreditCardUiState.One -> Unit
                is CreditCardUiState.Many -> {
                    Text(
                        "추가",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable { onNavigateToNewCard() }
                            .padding(end = 16.dp),
                        color = Color.Black
                    )
                }
            }
        }
    )
}

private class CreditCardUiStateProvider : PreviewParameterProvider<CreditCardUiState> {
    override val values: Sequence<CreditCardUiState> = sequenceOf(
        CreditCardUiState.Empty,
        CreditCardUiState.Loading,
        CreditCardUiState.One(
            CardInfo(
                number = "congue",
                expiredDate = "eam",
                ownerName = "Ronnie Curry",
                password = "gubergren",
                "카드회사"
            )
        ),
        CreditCardUiState.Many(
            listOf(
                CardInfo(
                    number = "congue",
                    expiredDate = "eam",
                    ownerName = "Ronnie Curry",
                    password = "gubergren",
                    "카드회사"
                ),
                CardInfo(
                    number = "congue",
                    expiredDate = "eam",
                    ownerName = "Ronnie Curry",
                    password = "gubergren",
                    cardName = "gubergren",
                    ),
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CreditCardTopBarPreview(@PreviewParameter(CreditCardUiStateProvider::class) uiState: CreditCardUiState) {
    PaymentsTheme {
        CreditCardTopBar(uiState = uiState, {})
    }
}
