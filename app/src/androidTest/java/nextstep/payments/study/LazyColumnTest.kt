package nextstep.payments.study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
                dummyItems.forEachIndexed { index, item ->
                    Text(
                        text = item,
                        modifier = Modifier.testTag("text $index")
                    )
                }
            }
        }

        // 보여지지 않는 노트가 모두 다 만들어지는지 테스트
//        composeTestRule
//            .onAllNodesWithTag("text")
//            .assertCountEquals(10000)

        // 첫번째 아이템인 text 0 부터 text 9999 까지 노드가 모두 존재하는지 테스트
        composeTestRule
            .onNodeWithTag("text 0")
            .assertExists()

        composeTestRule
            .onNodeWithTag("text 9999")
            .assertExists()
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

        val nodeSize: Int = composeTestRule
            .onAllNodesWithTag("text")
            .fetchSemanticsNodes()
            .size
        assert(nodeSize < 10000) // LazyColumn은 보이는 노드만 만들어진다 (52, 53)
    }

    @Test
    fun lazyColumnOnScroll() {
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

        Thread.sleep(1000)
        // 첫번째 아이템이 존재하고
        composeTestRule
            .onNodeWithTag("text 0")
            .assertExists()

        // 9999 로 스크롤하면
        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(9999)

        // 첫번째 아이템은 존재하지 않는다
        composeTestRule
            .onNodeWithTag("text 0")
            .assertDoesNotExist()

        // 보여지는 9999 번째 아이템은 존재한다
        composeTestRule
            .onNodeWithTag("text 9999")
            .assertExists()
    }

    companion object {
        private val dummyItems = List(10_000) { "Item $it" }
    }
}
