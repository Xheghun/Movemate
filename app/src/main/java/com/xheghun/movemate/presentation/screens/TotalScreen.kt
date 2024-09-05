package com.xheghun.movemate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xheghun.movemate.R
import com.xheghun.movemate.presentation.ui.Routes
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.colorGreen
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange

@Composable
fun TotalScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.movemate_logo),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(60.dp)
                .width(180.dp)
        )
        Image(
            painter = painterResource(R.drawable.move_box),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 12.dp)
                .size(180.dp)
        )
        Text("Total Estimated Amount", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(5)
        Row(verticalAlignment = Alignment.Bottom) {
            Text("$1458", fontSize = 20.sp, color = colorGreen, fontWeight = FontWeight.Medium)
            Text("USD", fontSize = 14.sp, color = colorGreen)
        }
        Text(
            "This amount is estimated this will vary if you change your location or weight",
            color = colorGreyText,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .width(220.dp)
                .padding(vertical = 12.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Routes.Home.name) },
            colors = ButtonDefaults.buttonColors(containerColor = colorOrange)
        ) {
            Text("Back to home", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}