package com.xheghun.movemate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xheghun.movemate.data.model.dummyShipments
import com.xheghun.movemate.presentation.custom_views.SearchTextField
import com.xheghun.movemate.presentation.ui.HorizontalRule
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight
import com.xheghun.movemate.presentation.ui.theme.colorGreyText

@Composable
fun SearchScreen(navController: NavController) {
    var searchValue by remember { mutableStateOf("") }
    Column {
        //HEADER SECTION
        Row(
            Modifier
                .background(bluePrimary)
                .padding(vertical = 20.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    "",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            SearchTextField(
                value = searchValue,
                onValueChange = { searchValue = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(width = 12)
        }

        LazyColumn(Modifier.padding(12.dp)) {
            items(dummyShipments()) {
                Column {
                    Row(Modifier.padding(vertical = 8.dp)) {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(
                                    CircleShape
                                )
                                .background(bluePrimary)
                                .padding(
                                    8.dp
                                )
                        )

                        Spacer(width = 8)

                        Column {
                            Text(it.itemName, fontWeight = FontWeight.Medium)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(it.shipmentNumber, fontSize = 12.sp, color = colorGreyText)
                                Box(
                                    Modifier
                                        .padding(horizontal = 4.dp)
                                        .clip(CircleShape)
                                        .background(colorGreyText)
                                        .padding(2.dp)
                                )
                                Text(it.sender, fontSize = 12.sp, color = colorGreyText)
                                Icon(
                                    Icons.Filled.ArrowForward,
                                    contentDescription = "",
                                    tint = colorGreyText,
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .size(16.dp)
                                )
                                Text(it.receiver, fontSize = 12.sp, color = colorGreyText)
                            }
                        }
                    }
                    HorizontalRule(color = colorGreyLight)
                }
            }
        }
    }
}