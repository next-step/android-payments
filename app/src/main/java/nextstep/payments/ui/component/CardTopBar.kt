package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAddTopBar(
    title: String,
    onBackClick: () -> Unit,
    onCheckClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Title(
                title = title,
                textAlign = TextAlign.Start,
            )
        },
        navigationIcon = {
            BackButton(onBackClick)
        },
        actions = {
            TopBarIcon(
                onClick = onCheckClick,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(R.string.check)
                    )
                }
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Title(
                title = stringResource(R.string.payments),
                textAlign = TextAlign.Center,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    onAddClick: (() -> Unit),
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Title(
                title = stringResource(R.string.payments),
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            TopBarIcon(
                onClick = onAddClick,
                icon = {
                    Text(text = stringResource(R.string.add))
                }
            )
        },
        navigationIcon = {
            Spacer(modifier = Modifier.size(iconSize))
        }
    )
}

private val iconSize = 50.dp

@Composable
private fun BackButton(onBackClick: () -> Unit) {
    TopBarIcon(
        onClick = onBackClick,
        icon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
    )
}

@Composable
private fun Title(title: String, textAlign: TextAlign) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign
    )
}

@Composable
private fun TopBarIcon(
    icon: (@Composable BoxScope.() -> Unit),
    onClick: (() -> Unit),
) {
    Box(
        modifier = Modifier
            .size(iconSize)
            .then(Modifier.clickable { onClick.invoke() }),
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}

@Preview
@Composable
private fun CardTopBarPreview() {
    PaymentsTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CardAddTopBar(
                title = stringResource(R.string.add_card),
                onBackClick = {},
                onCheckClick = {},
            )
            CardListTopBar(
                onAddClick = {},
            )
        }
    }
}
