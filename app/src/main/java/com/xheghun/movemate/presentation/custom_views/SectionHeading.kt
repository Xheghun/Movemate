package com.xheghun.movemate.presentation.custom_views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.xheghun.movemate.presentation.ui.theme.colorGreyText

@Composable
fun SectionHeading(title: String, subTitle: String) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
        if(subTitle.isNotEmpty())
        Text(
            text = title,
            color = colorGreyText,
            fontSize = 16.sp
        )

    }
}