package com.marlonmafra.segmentedTab

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Immutable
data class SegmentedTabAppearance(
  val height: Dp,
  val segmentShape: Shape,
  val borderWidth: Dp,
  val borderColor: Color,
  val segmentPadding: PaddingValues,
  val segmentBackgroundColor: Color,
  val selectedSegmentedBackgroundColor: Color,
  val selectedSegmentTextStyle: TextStyle,
  val unselectedSegmentTextStyle: TextStyle,
  val selectedSegmentTextColor: Color,
  val unselectedSegmentTextColor: Color,
  val contentPadding: PaddingValues,
) {

  @Stable
  @Composable
  fun textColor(isSelected: Boolean): State<Color> {
    val color = if (isSelected) selectedSegmentTextColor else unselectedSegmentTextColor
    return animateColorAsState(color)
  }

  @Stable
  fun textStyle(selected: Boolean): TextStyle {
    return if (selected) selectedSegmentTextStyle else unselectedSegmentTextStyle
  }
}
