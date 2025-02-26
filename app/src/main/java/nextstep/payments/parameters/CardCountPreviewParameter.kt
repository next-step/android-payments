package nextstep.payments.parameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.cardlist.CardCount

class CardCountPreviewParameter: PreviewParameterProvider<CardCount> {
    override val values = CardCount.entries.asSequence()
}