package nextstep.payments.study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import org.junit.Rule
import org.junit.Test

class LazyColumnTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun column() {
        composeTestRule.setContent {
            Column {
                items.forEach {
                    Text(
                        text = it,
                        modifier = Modifier.testTag("text"),
                    )
                }
            }
        }

        composeTestRule
            .onAllNodesWithTag("text")
            .assertCountEquals(10_000)
    }

    @Test
    fun lazyColumn() {
        composeTestRule.setContent {
            LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
                items(items.size) {
                    Text(
                        text = items[it],
                        modifier = Modifier.testTag("text"),
                    )
                }
            }
        }
        val nodeSize = composeTestRule.nodeSize("text")

        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(9_999)

        val nodeSizeAfterScroll = composeTestRule.nodeSize("text")
        assert(nodeSize == nodeSizeAfterScroll)
        assert(nodeSizeAfterScroll < 10_000)
    }

    @Test
    fun lazyColumn2() {
        composeTestRule.setContent {
            LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
                items(items.size) {
                    Text(
                        text = items[it],
                        modifier = Modifier.testTag("text $it"),
                    )
                }
            }
        }
        composeTestRule
            .onNodeWithTag("text 0")
            .assertExists()

        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(9_999)

        composeTestRule
            .onNodeWithTag("text 0")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("text 9999")
            .assertExists()
    }

    private fun ComposeTestRule.nodeSize(textTag: String): Int =
        onAllNodesWithTag(textTag)
            .fetchSemanticsNodes()
            .size

    companion object {
        private val items = List(10_000) { "Item $it" }
    }
}
