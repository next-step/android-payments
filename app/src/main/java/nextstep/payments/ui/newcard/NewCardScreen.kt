package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.edit.EditableCardPage
import nextstep.payments.ui.newcard.component.BankBottomSheetContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bank: CardBankInformation,
    isShowBanks: Boolean,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onAddCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onBankClick: (CardBankInformation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    LaunchedEffect(key1 = isShowBanks) {
        if (isShowBanks) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    // 카드 추가 화면
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.net_card_top_bar_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAddCardClick) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "완료",
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            EditableCardPage(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                bank = bank,
                setCardNumber = setCardNumber,
                setExpiredDate = setExpiredDate,
                setOwnerName = setOwnerName,
                setPassword = setPassword,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            )
        },
    )

    if (isShowBanks) {
        ModalBottomSheet(
            modifier = Modifier.semantics {
                contentDescription = "BankBottomSheet"
            },
            onDismissRequest = { },
            properties = ModalBottomSheetDefaults.properties(
                shouldDismissOnBackPress = false,
            ),
            sheetState = bottomSheetState,
        ) {
            BankBottomSheetContent(
                onBankClick = {
                    onBankClick(it)
                },
            )
        }
    }
}

// Card information data class for previews
private data class CardPreviewData(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bank: CardBankInformation
)

private class CardPreviewProvider : PreviewParameterProvider<CardPreviewData> {
    override val values = sequenceOf(
        CardPreviewData(
            cardNumber = "1111222233334444",
            expiredDate = "1234",
            ownerName = "홍길동",
            password = "1234",
            bank = CardBankInformation.None
        ),
        CardPreviewData(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            bank = CardBankInformation.None
        ),
    ) + CardBankInformation.entries.map {
        CardPreviewData(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            bank = it
        )
    }
}

@Preview
@Composable
private fun PreviewNewCardScreen(
    @PreviewParameter(CardPreviewProvider::class) cardPreviewData: CardPreviewData
) {
    val cardNumber = remember { mutableStateOf(cardPreviewData.cardNumber) }
    val expiredDate = remember { mutableStateOf(cardPreviewData.expiredDate) }
    val ownerName = remember { mutableStateOf(cardPreviewData.ownerName) }
    val password = remember { mutableStateOf(cardPreviewData.password) }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        bank = cardPreviewData.bank,
        isShowBanks = false,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        onBankClick = {},
        modifier = Modifier.fillMaxSize()
    )
}