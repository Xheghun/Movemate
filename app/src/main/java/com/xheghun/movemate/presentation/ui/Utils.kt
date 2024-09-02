package com.xheghun.movemate.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Spacer(height: Int = 0, width: Int = 0) {
    Box(modifier = Modifier
        .height(height.dp)
        .width(width.dp))
}