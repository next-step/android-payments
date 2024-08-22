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
                    Text(
                        text = it,
                        modifier = Modifier.testTag("text")
                    )
                }
            }
        }

        composeTestRule
            .onAllNodesWithTag("text")
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

        val nodeSize = composeTestRule
            .onAllNodesWithTag("text")
            .fetchSemanticsNodes()
            .size

        assert(nodeSize < 10000)
    }

    @Test
    fun lazyColumn2() {
        composeTestRule.setContent {
            LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
                items(dummyItems.size) {
                    Text(
                        text = dummyItems[it],
                        modifier = Modifier.testTag("text_$it")
                    )
                }
            }
        }

        composeTestRule
            .onNodeWithTag("text_0")
            .assertExists()

        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(9999)

        composeTestRule
            .onNodeWithTag("text_0")
            .assertDoesNotExist()
    }

    companion object {
        private val dummyItems: List<String> = List(10000) { "Item $it" }
    }
}