package com.marlonmafra.segmentedTab.model

import androidx.compose.ui.graphics.painter.Painter

data class SegmentedTabData(
    val type: String,
    val title: String,
    val icon: Painter? = null
)
