package com.marlonmafra.segmentedTab.model

data class SegmentedTabAction(
    val onSegmentSelected: (Int) -> Unit
)
