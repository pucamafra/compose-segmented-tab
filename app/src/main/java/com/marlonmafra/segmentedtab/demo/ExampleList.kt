package com.marlonmafra.segmentedtab.demo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.marlonmafra.segmentedtab.demo.navigation.DemoRoute

@Composable
fun ExampleList(
    onItemClicked: (String) -> Unit
) {
    LazyColumn {
        item {
            ItemList("Title Only") {
                onItemClicked(DemoRoute.TitleOnly.route)
            }

            ItemList("Title With Icon") {
                onItemClicked(DemoRoute.TitleWithIcon.route)
            }

            ItemList("Swipeable Content") {
                onItemClicked(DemoRoute.SwipeableContent.route)
            }
        }
    }
}

@Composable
private fun ItemList(title: String, onClick: () -> Unit) {
    Row {
        TextButton(onClick) {
            Text(title)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExampleListPreview() {
    ExampleList(onItemClicked = { })
}
