package nextstep.payments.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.viewmodel.CardListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import nextstep.payments.data.model.Card
import nextstep.payments.ui.CardUiState
import nextstep.payments.ui.screen.component.PaymentCard
import nextstep.payments.R
import nextstep.payments.ui.screen.component.CenterTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    navigateToNewCard: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel(),
) {
    val cards by viewModel.cards.collectAsState()
    val cardsState = cards

    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.card_app_title),
                        fontWeight = FontWeight.W400,
                        fontSize = 22.sp
                    )
                },
                actions = {
                    CenterTopBar(
                        composeActionButton = {
                            if (cardsState is CardUiState.Many) {
                                IconButton(
                                    onClick = navigateToNewCard
                                ) {
                                    Text(
                                        text = stringResource(R.string.app_bar_add_action_button),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W700,
                                    )
                                }
                            }
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            when (cardsState) {
                is CardUiState.Empty -> {
                    Text(
                        text = stringResource(R.string.new_card_register_text),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    AddCardContainer(
                        onClick = navigateToNewCard
                    )
                }

                is CardUiState.One -> {
                    PaymentCard(
                        cardNumber = cardsState.data.cardNumber,
                        expiredDate = cardsState.data.expiredDate,
                        ownerName = cardsState.data.ownerName,
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    AddCardContainer(
                        onClick = navigateToNewCard
                    )
                }

                is CardUiState.Many -> {
                    CardListContainer(
                        cardList = cardsState.data
                    )
                }
            }
        }
    }
}

@Composable
private fun CardListContainer(
    cardList: List<Card>,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        contentPadding = PaddingValues(12.dp),
    ) {
        items(cardList) { card ->
            PaymentCard(
                cardNumber = card.cardNumber,
                expiredDate = card.expiredDate,
                ownerName = card.ownerName,
            )
        }
    }
}

@Composable
private fun AddCardContainer(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.register_card_icon_content_description),
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCardContainerPreview() {
    AddCardContainer(onClick = {})
}

@Preview(showBackground = true)
@Composable
private fun CardListConatinerPreview() {
    CardListContainer(
        cardList = listOf(
            Card(
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "12421412"
            ),
            Card(
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "12421412"
            ),
            Card(
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "12421412"
            ),
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        navigateToNewCard = {}
    )
}