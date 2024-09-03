package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.component.AddableCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CardListEmptyScreen(
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        topBar = { CardListTopAppBar() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.card_list_empty_screen_title),
                    style = PaymentsTheme.typography.sans18B.copy(letterSpacing = 0.5.sp),
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(32.dp))

                AddableCard(onClick = onAddCardClick)
            }
        }
    )
}