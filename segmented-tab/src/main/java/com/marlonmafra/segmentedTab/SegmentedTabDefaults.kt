package com.marlonmafra.segmentedTab

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val defaultHeight = 40.dp
private val defaultSegmentShape = RoundedCornerShape(8.dp)
private val defaultBorderWidth = 1.dp
private val defaultBorderColor = Color.LightGray
private val defaultSegmentPadding = PaddingValues(all = 4.dp)
private val defaultSegmentBackgroundColor = Color.LightGray
private val defaultSelectedSegmentedBackgroundColor = Color.White
private val defaultSelectedSegmentTextColor = Color.Black
private val defaultUnselectedSegmentTextColor = Color.DarkGray
private val defaultContentPadding: PaddingValues =
  PaddingValues(horizontal = 16.dp, vertical = 8.dp)

object SegmentedTabDefaults {

  private var defaultSegmentTabAppearanceCached: SegmentedTabAppearance? = null

  private val appearance: SegmentedTabAppearance
    get() {
      return defaultSegmentTabAppearanceCached
        ?: SegmentedTabAppearance(
          height = defaultHeight,
          segmentShape = defaultSegmentShape,
          borderWidth = defaultBorderWidth,
          borderColor = defaultBorderColor,
          segmentPadding = defaultSegmentPadding,
          segmentBackgroundColor = defaultSegmentBackgroundColor,
          selectedSegmentedBackgroundColor = defaultSelectedSegmentedBackgroundColor,
          selectedSegmentTextStyle = TextStyle.Default,
          unselectedSegmentTextStyle = TextStyle.Default,
          selectedSegmentTextColor = defaultSelectedSegmentTextColor,
          unselectedSegmentTextColor = defaultUnselectedSegmentTextColor,
          contentPadding = defaultContentPadding
        ).also { defaultSegmentTabAppearanceCached = it }
    }

  fun appearance() = appearance

  fun appearance(
    height: Dp = defaultHeight,
    segmentShape: Shape = defaultSegmentShape,
    borderWidth: Dp = defaultBorderWidth,
    borderColor: Color = defaultBorderColor,
    segmentPadding: PaddingValues = defaultSegmentPadding,
    segmentBackgroundColor: Color = defaultSegmentBackgroundColor,
    selectedSegmentedBackgroundColor: Color = defaultSelectedSegmentedBackgroundColor,
    selectedSegmentTextStyle: TextStyle = TextStyle.Default,
    unselectedSegmentTextStyle: TextStyle = TextStyle.Default,
    selectedSegmentTextColor: Color = defaultSelectedSegmentTextColor,
    unselectedSegmentTextColor: Color = defaultUnselectedSegmentTextColor,
    contentPaddingValues: PaddingValues = defaultContentPadding
  ): SegmentedTabAppearance = appearance.copy(
    height = height,
    segmentShape = segmentShape,
    borderWidth = borderWidth,
    borderColor = borderColor,
    segmentPadding = segmentPadding,
    segmentBackgroundColor = segmentBackgroundColor,
    selectedSegmentedBackgroundColor = selectedSegmentedBackgroundColor,
    selectedSegmentTextStyle = selectedSegmentTextStyle,
    unselectedSegmentTextStyle = unselectedSegmentTextStyle,
    selectedSegmentTextColor = selectedSegmentTextColor,
    unselectedSegmentTextColor = unselectedSegmentTextColor,
    contentPadding = contentPaddingValues
  )
}
