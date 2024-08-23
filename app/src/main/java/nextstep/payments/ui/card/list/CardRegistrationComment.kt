package nextstep.payments.ui.card.list

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import nextstep.payments.R

@Composable
fun CardRegistrationComment(comment: String, modifier: Modifier = Modifier) {
    Text(
        text = comment,
        modifier = modifier.testTag(stringResource(id = R.string.test_tag_comment)),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
    )
}
