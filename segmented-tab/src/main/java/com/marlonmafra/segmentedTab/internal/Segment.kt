package com.marlonmafra.segmentedTab.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marlonmafra.segmentedTab.R

@Composable
internal fun RowScope.Segment(
    title: String,
    icon: Painter? = null,
    height: Dp,
    textColor: Color,
    textStyle: TextStyle,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(height)
            .weight(1f)
            .clickable(
                role = Role.Button,
                enabled = !isSelected,
                indication = null,
                interactionSource = null,
                onClickLabel = title,
                onClick = onClick
            )
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon?.let {
            Icon(
                modifier = Modifier.padding(end = dimensionResource(R.dimen.icon_padding_end)),
                painter = it,
                contentDescription = null
            )
        }

        Box(Modifier) {
            Text(
                text = title,
                modifier = Modifier
                    .semantics {
                        contentDescription = contentDescription(title, isSelected)
                    },
                maxLines = 1,
                overflow = Ellipsis,
                color = textColor,
                style = textStyle
            )
        }
    }
}

private fun contentDescription(title: String, isSelected: Boolean): String {
    return if (isSelected) {
        "Selected $title segment"
    } else {
        "Unselected $title segment"
    }
}

@Composable
@Preview(showBackground = true)
private fun SegmentPreview() {
    Row {
        Segment(
            title = "Title",
            textColor = Color.DarkGray,
            textStyle = TextStyle.Default,
            height = 40.dp,
            isSelected = false,
            onClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SegmentWithIconPreview() {
    Row {
        Segment(
            title = "Title",
            icon = painterResource(android.R.drawable.ic_delete),
            textColor = Color.DarkGray,
            textStyle = TextStyle.Default,
            height = 40.dp,
            isSelected = false,
            onClick = { }
        )
    }
}