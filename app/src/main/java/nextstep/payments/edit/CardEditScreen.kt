package nextstep.payments.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.component.PaymentCard
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardEditScreen(
    navigateToList: () -> Unit,
    navigateToListWithEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardEditViewModel = viewModel(),
) {
    val state by viewModel.cardEditState.collectAsStateWithLifecycle()

    CardEditScreen(
        modifier = modifier,
        card = state.editCard,
        saveEnabled = state.saveEnabled,
        setNumber = viewModel::setNumber,
        setDueDate = viewModel::setDueDate,
        setName = viewModel::setName,
        setPassword = viewModel::setPassword,
        onBackClick = navigateToList,
        onSaveClick = navigateToListWithEdit
    )
}

@Composable
fun CardEditScreen(
    card: CreditCard,
    saveEnabled: Boolean,
    setNumber: (String) -> Unit,
    setDueDate: (String) -> Unit,
    setName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            CardEditTopBar(
                saveEnabled = saveEnabled,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(card = card)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = card.number,
                onValueChange = setNumber,
                label = { Text("카드 번호") },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.dueDate,
                onValueChange = setDueDate,
                label = { Text("만료일") },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.name,
                onValueChange = setName,
                label = { Text("카드 소유자 이름(선택)") },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.password,
                onValueChange = setPassword,
                label = { Text("비밀번호") },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}


@Preview
@Composable
private fun CardEditScreenPreview() {
    PaymentsTheme {
        CardEditScreen(
            card = CreditCard.emptyCard,
            saveEnabled = false,
            setNumber = {},
            setDueDate = {},
            setName = {},
            setPassword = {},
            onBackClick = {},
            onSaveClick = {}
        )
    }
}