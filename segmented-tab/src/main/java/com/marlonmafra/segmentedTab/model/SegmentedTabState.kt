package com.marlonmafra.segmentedTab.model

data class SegmentedTabState(
    val enabled: Boolean = true,
    val segments: List<SegmentedTabData>,
    val selectedSegment: Int,
)