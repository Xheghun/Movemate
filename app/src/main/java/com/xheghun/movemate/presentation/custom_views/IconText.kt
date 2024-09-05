package com.xheghun.movemate.presentation.custom_views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.OpenSans

@Composable
fun IconText(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    iconSize: Int = 16,
    color: Color = Color.White,
    fontSize: Int = 14,
    fontWeight: FontWeight? = null,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        if (startIcon != null) {
            Icon(
                startIcon,
                contentDescription = "$text icon",
                tint = color,
                modifier =
                Modifier.size(iconSize.dp)
            )
        }

        if (startIcon != null) {
            Spacer(width = 4)
        }

        Text(
            text = text,
            fontFamily = OpenSans,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            color = color
        )

        if (endIcon != null) {
            Spacer(width = 4)
        }

        if (endIcon != null)
            Icon(
                endIcon,
                contentDescription = "$text icon",
                tint = color,
                modifier =
                Modifier.size(iconSize.dp)
            )

    }
}