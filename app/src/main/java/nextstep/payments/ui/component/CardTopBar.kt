package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardTopBar(
    title: String,
    titleTextAlign: TextAlign,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit),
    actionButton: @Composable RowScope.() -> Unit,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = titleTextAlign
            )
        },
        navigationIcon = {
            TopBarIcon(
                onClick = onBackClick?.let { onBackClick },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        },
        actions = {
            actionButton()

        },
    )
}

@Composable
fun CardAddTopBar(
    onBackClick: () -> Unit,
    onCheckClick: () -> Unit,
) {
    CardTopBar(
        title = stringResource(R.string.add),
        titleTextAlign = TextAlign.Start,
        onBackClick = onBackClick,
        actionButton = {
            TopBarIcon(
                onClick = onCheckClick,
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.check),
            )
        },
    )
}

@Composable
fun CardListTopBar(
    onAddClick: (() -> Unit)?,
) {
    CardTopBar(
        title = stringResource(R.string.payments),
        titleTextAlign = TextAlign.Center,
        actionButton = {
            TopBarActionText(
                onClick = onAddClick,
                text = stringResource(R.string.add)
            )
        },
        onBackClick = {},
    )
}

@Composable
private fun TopBarIcon(
    onClick: (() -> Unit)?,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .then(if (onClick != null) Modifier.clickable { onClick.invoke() } else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        onClick?.let {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
            )
        }
    }
}

@Composable
private fun TopBarActionText(
    onClick: (() -> Unit)?,
    text: String,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .then(if (onClick != null) Modifier.clickable { onClick.invoke() } else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        onClick?.let {
            Text(text = text)
        }
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
                onBackClick = {},
                onCheckClick = {},
            )
            CardListTopBar(
                onAddClick = {},
            )
        }
    }
}
