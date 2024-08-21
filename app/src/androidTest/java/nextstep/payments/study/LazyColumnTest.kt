package nextstep.payments.study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
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
                dummyItems.forEach {
                    Text(text = it, modifier = Modifier.testTag("text"))
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
                items(dummyItems.size) {
                    Text(
                        text = dummyItems[it],
                        modifier = Modifier.testTag("text")
                    )
                }
            }
        }

        val size = composeTestRule.onAllNodesWithTag("text")
            .fetchSemanticsNodes()
            .size
        assert(size < 10000) //53
    }

    @Test
    fun lazyColumn2() {
        composeTestRule.setContent {
            LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
                items(dummyItems.size) {
                    Text(
                        text = dummyItems[it],
                        modifier = Modifier.testTag("text $it")
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("text 0")
            .assertExists()

        composeTestRule.onNodeWithTag("lazyColumn")
            .performScrollToIndex(9999)

        composeTestRule.onNodeWithTag("text 0")
            .assertDoesNotExist()

        composeTestRule.onNodeWithTag("text 9999")
            .assertExists()
    }

    companion object {
        private val dummyItems: List<String> = List(10000) { "item $it" }
    }
}
