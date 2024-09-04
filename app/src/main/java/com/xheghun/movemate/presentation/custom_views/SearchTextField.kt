package com.xheghun.movemate.presentation.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.movemate.presentation.ui.theme.Satoshi
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "Enter the receipt number ...",
    enabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "search icon",
            modifier = Modifier.padding(6.dp),
            tint = bluePrimary
        )
        val searchTextStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = Satoshi,
            fontWeight = FontWeight.Normal
        )
        BasicTextField(
            value = value,
            singleLine = true,
            readOnly = true,
            enabled = enabled,
            textStyle = searchTextStyle,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        color = colorGreyText,
                        style = searchTextStyle,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                } else {
                    Box(modifier = Modifier.padding(vertical = 15.dp)) {
                        innerTextField()
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
        )
        Box(
            Modifier
                .clip(CircleShape)
                .background(colorOrange)
                .padding(6.dp)
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "suffix icon",
                tint = Color.White
            )
        }
    }
}