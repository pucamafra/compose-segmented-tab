package com.marlonmafra.segmentedtab.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.marlonmafra.segmentedTab.SegmentedTab
import com.marlonmafra.segmentedTab.model.SegmentedTabAction
import com.marlonmafra.segmentedTab.model.SegmentedTabData
import com.marlonmafra.segmentedTab.model.SegmentedTabState

@Composable
fun TitleOnly() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var selectedSegment by remember { mutableIntStateOf(0) }
        val action = SegmentedTabAction(onSegmentSelected = {
            selectedSegment = it
        })

        val segments = listOf(
            SegmentedTabData(
                type = "Title1",
                title = "Title1",
            ),
            SegmentedTabData(
                type = "Title2",
                title = "Title2",
            ),
            SegmentedTabData(
                type = "Title3",
                title = "Title3",
            )
        )

        Column {
            SegmentedTab(
                state = SegmentedTabState(
                    segments = segments,
                    selectedSegment = selectedSegment,
                ),
                action = action
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.LightGray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Selected type:${selectedSegment.plus(1)}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleOnlyPreview() {
    TitleOnly()
}