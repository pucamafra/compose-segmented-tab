package com.marlonmafra.segmentedTab

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.marlonmafra.segmentedTab.internal.Segment
import com.marlonmafra.segmentedTab.internal.SelectedSegment
import com.marlonmafra.segmentedTab.model.SegmentedTabAction
import com.marlonmafra.segmentedTab.model.SegmentedTabData
import com.marlonmafra.segmentedTab.model.SegmentedTabState

@Composable
fun SegmentedTab(
  state: SegmentedTabState,
  action: SegmentedTabAction,
  appearance: SegmentedTabAppearance = SegmentedTabDefaults.appearance()
) {
  val segments = state.segments
  val selectedIndexOffset by animateFloatAsState(state.selectedSegment.toFloat())
  val containerColor = appearance.selectedSegmentBackgroundColor
  val segmentPadding = appearance.segmentPadding
  val segmentShape = appearance.segmentShape
  val segmentBackgroundColor = appearance.segmentBackgroundColor
  val contentPadding = appearance.contentPadding
  val height = appearance.height
  val borderWidth = appearance.borderWidth
  val borderColor = appearance.borderColor

  Column(modifier = Modifier.padding(contentPadding)) {
    LookaheadScope {
      Layout(
        content = {
          SelectedSegment(containerColor, segmentShape)
          Segments(
            appearance = appearance,
            height = height,
            selectedSegment = state.selectedSegment,
            segments = segments,
            onSegmentClicked = action.onSegmentSelected
          )
        },
        modifier = Modifier
          .border(borderWidth, borderColor, segmentShape)
          .background(segmentBackgroundColor, segmentShape)
          .padding(segmentPadding)
      ) { measurables, constraints ->
        val (selectedSegmentMeasurable, segmentsMeasurable) = measurables

        val segmentsPlaceable = segmentsMeasurable.measure(constraints)
        val selectedSegmentPlaceable = selectedSegmentMeasurable.measure(
          Constraints.fixed(
            width = segmentsPlaceable.width / segments.size,
            height = segmentsPlaceable.height
          )
        )

        layout(segmentsPlaceable.width, segmentsPlaceable.height) {
          val segmentWidth = segmentsPlaceable.width / segments.size

          selectedSegmentPlaceable.placeRelative(
            x = (selectedIndexOffset * segmentWidth).toInt(),
            y = 0
          )

          segmentsPlaceable.placeRelative(IntOffset.Zero)
        }
      }
    }
  }
}

@Composable
private fun Segments(
  appearance: SegmentedTabAppearance,
  height: Dp,
  selectedSegment: Int,
  segments: List<SegmentedTabData>,
  onSegmentClicked: (Int) -> Unit
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.selectableGroup()
  ) {
    segments.forEachIndexed { index, segment ->
      val isSelected = index == selectedSegment
      val textColor by appearance.textColor(isSelected)
      val textStyle = appearance.textStyle(isSelected)

      Segment(
        title = segment.title,
        icon = segment.icon,
        textColor = textColor,
        textStyle = textStyle,
        height = height,
        isSelected = isSelected,
        onClick = { onSegmentClicked(index) }
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
private fun SegmentedTabPreview() {
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

  SegmentedTab(
    state = SegmentedTabState(segments = segments, selectedSegment = selectedSegment),
    action = action
  )
}
