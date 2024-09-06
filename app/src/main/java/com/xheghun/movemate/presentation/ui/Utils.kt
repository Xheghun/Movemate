package com.xheghun.movemate.presentation.ui

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight


@Composable
fun Spacer(height: Int = 0, width: Int = 0) {
    Box(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}

@Composable
fun HorizontalRule(color: Color = colorGreyLight, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(color)
            .height(1.dp)
            .fillMaxWidth()
    )
}

@Composable
fun updateStatusBarColor(color: Int) {
    val context = LocalContext.current as ComponentActivity

    DisposableEffect(Unit) {
        context.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                color,
                color
            )
        )

        onDispose { }
    }
}