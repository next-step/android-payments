package nextstep.payments.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import nextstep.payments.ui.utils.BackgroundColorKey

fun SemanticsNodeInteraction.assertBackgroundColor(expectedColor: Color): SemanticsNodeInteraction {
    return assert(
        SemanticsMatcher(
            description = "Expected background color to be $expectedColor"
        ) { semanticsNode ->
            val actualColor = semanticsNode.config.getOrNull(BackgroundColorKey)
            actualColor == expectedColor
        }
    )
}
