package nextstep.payments.study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
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
                        modifier = Modifier.testTag("text"),
                        text = it,
                    )
                }
            }
        }

        composeTestRule.onAllNodesWithTag("text")
            .assertCountEquals(10000)
    }

    @Test
    fun lazyColumn() {
        composeTestRule.setContent {
            LazyColumn {
                items(items = items) {
                    Text(
                        modifier = Modifier.testTag("text"),
                        text = it,
                    )
                }
            }
        }

        val nodeSize = composeTestRule.onAllNodesWithTag("text")
            .fetchSemanticsNodes()
            .size
        assert(nodeSize < 100)
    }

    @Test
    fun lazyColumn2() {
        composeTestRule.setContent {
            LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
                items(items = items) {
                    Text(
                        modifier = Modifier.testTag(it),
                        text = it,
                    )
                }
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("Item 0")
            .assertExists()

        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(9999)

        composeTestRule
            .onNodeWithTag("Item 0")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("Item 9999")
            .assertExists()
    }

    companion object {
        private val items = List(10000) {
            "Item $it"
        }
    }
}
