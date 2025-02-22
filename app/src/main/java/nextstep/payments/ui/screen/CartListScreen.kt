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
import nextstep.payments.ui.CardUiState
import nextstep.payments.ui.screen.component.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel(),
    navigateToNewCard: () -> Unit,
) {
    val cards by viewModel.cards.collectAsState()
    val cardsState = cards

    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Payments", fontWeight = FontWeight.W400, fontSize = 22.sp) },
                actions = {
                    if(cardsState is CardUiState.Many) {
                        IconButton(
                            onClick = navigateToNewCard
                        ) {
                            Text(
                                text = "추가",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W700,
                            )
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            when(cardsState) {
                is CardUiState.Empty -> {
                    Text(
                        text = "새로운 카드를 등록해주세요",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    AddCardContainer(
                        onClick = navigateToNewCard
                    )
                }
                is CardUiState.One -> {
                    PaymentCard()
                    Spacer(modifier = Modifier.height(36.dp))
                    AddCardContainer(
                        onClick = navigateToNewCard
                    )
                }
                is CardUiState.Many -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(36.dp),
                        contentPadding = PaddingValues(12.dp),
                    ) {
                        items(cardsState.data.size) { index ->
                            PaymentCard()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddCardContainer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
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
            contentDescription = "Cart",
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardContainerPreview() {
    AddCardContainer(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun CardListScreenPreview() {
    CardListScreen(
        navigateToNewCard = {}
    )
}