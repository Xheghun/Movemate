package com.xheghun.movemate.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xheghun.movemate.presentation.custom_views.SearchTextField
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary

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
                Icon(Icons.Filled.KeyboardArrowLeft, "", tint = Color.White)
            }
            SearchTextField(value = searchValue, onValueChange = { searchValue = it })
            Spacer(width = 12)
        }
    }
}