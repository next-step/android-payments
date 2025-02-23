package nextstep.payments.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.CardCompany
import nextstep.payments.data.model.cardCompanyList
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAddTopBar
import nextstep.payments.ui.component.CardInputField
import nextstep.payments.ui.component.CardSelectSheet
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toCardList
import nextstep.payments.viewmodel.CardAddViewModel
import nextstep.payments.viewmodel.CardCompanyBottomSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAddScreen(
    card: Card,
    cardAdded: Long,
    bottomSheetState: CardCompanyBottomSheetState,
    onCardCompanyChange: (CardCompany) -> Unit,
    onCardCompanyBottomSheetState: (CardCompanyBottomSheetState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    onAddClicked: () -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(cardAdded) {
        if (card.company != null) {
            context.showToast(context.getString(R.string.success_add_card))
            context.toCardList()
        } else {
            context.showToast(context.getString(R.string.request_select_card_company))
            onCardCompanyBottomSheetState(CardCompanyBottomSheetState.Show)
        }
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    Scaffold(
        topBar = {
            CardAddTopBar(
                onBackClick = onBackPressed,
                onCheckClick = { onAddClicked() },
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 14.dp)
            ) {

                Card(model = card)
                CardInputFields(
                    card = card,
                    onCardNumberChange = onCardNumberChange,
                    onExpiredDateChange = onExpiredDateChange,
                    onOwnerNameChange = onOwnerNameChange,
                    onPasswordChange = onPasswordChange,
                )
            }

            if (bottomSheetState == CardCompanyBottomSheetState.Show) {
                ModalBottomSheet(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = { onCardCompanyBottomSheetState(CardCompanyBottomSheetState.Hide) },
                ) {
                    CardSelectSheet(
                        cardCompanyList = cardCompanyList,
                        selectedCompany = card.company,
                        onCardCompanyChange = onCardCompanyChange
                    )
                }
            }
        }
    }
}

private val Context.toast get() = Toast.makeText(this, "", Toast.LENGTH_SHORT)
private fun Context.showToast(message: String) {
    toast.apply {
        setText(message)
        show()
    }
}

@Composable
fun CardAddScreen(
    onBackPressed: () -> Unit,
    viewModel: CardAddViewModel = viewModel(),
) {

    val card by viewModel.card.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()

    CardAddScreen(
        card = card,
        cardAdded = cardAdded,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setCardNumber,
        onOwnerNameChange = viewModel::setCardNumber,
        onPasswordChange = viewModel::setCardNumber,
        onBackPressed = { onBackPressed.invoke() },
        onAddClicked = viewModel::addCard,
        bottomSheetState = cardCompanyBottomSheet,
        onCardCompanyChange = viewModel::setCardCompany,
        onCardCompanyBottomSheetState = viewModel::setCardCompanyBottomSheetState,
    )
}

@Composable
private fun CardInputFields(
    card: Card,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 24.dp)
    ) {
        CardInputField(
            value = card.number,
            onValueChange = onCardNumberChange,
            labelText = stringResource(R.string.card_number_label),
            placeHolderText = stringResource(R.string.card_number_placeholder),
        )

        CardInputField(
            value = card.expiredDate,
            onValueChange = onExpiredDateChange,
            labelText = stringResource(R.string.card_expired_date_label),
            placeHolderText = stringResource(R.string.card_expired_date_placeholder),
        )

        CardInputField(
            value = card.ownerName,
            onValueChange = onOwnerNameChange,
            labelText = stringResource(R.string.card_owner_name_label),
            placeHolderText = stringResource(R.string.card_owner_name_placeholder),
        )

        CardInputField(
            value = card.password,
            onValueChange = onPasswordChange,
            labelText = stringResource(R.string.card_password_label),
            placeHolderText = stringResource(R.string.card_password_placeholder),
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        CardAddScreen(onBackPressed = {})
    }
}