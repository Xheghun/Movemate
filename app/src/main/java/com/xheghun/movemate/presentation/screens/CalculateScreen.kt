package com.xheghun.movemate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xheghun.movemate.R
import com.xheghun.movemate.presentation.custom_views.MoveTextField
import com.xheghun.movemate.presentation.custom_views.SectionHeading
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.hintTextStyle

@Composable
fun CalculateScreen(navController: NavController) {
    Column(Modifier.fillMaxWidth()) {
        //HEADER
        Box(
            Modifier
                .background(bluePrimary)
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Calculate",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "nav back",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { }
                    .padding(6.dp)
                    .align(Alignment.CenterStart)
            )
        }


        //BODY
        Column(Modifier.padding(12.dp)) {

            SectionHeading("Packaging", "")

            Spacer(8)
            Card(
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                IconTextField(
                    value = "",
                    onValueChange = {},
                    hintText = "Sender Location",
                    icon = Icons.Default.KeyboardArrowDown
                )
                IconTextField(
                    value = "",
                    onValueChange = {},
                    hintText = "Receiver Location",
                    icon = Icons.Default.KeyboardArrowDown
                )
                IconTextField(
                    value = "",
                    onValueChange = {},
                    hintText = "Approx Weight",
                    icon = Icons.Default.KeyboardArrowDown
                )
            }
            Spacer(12)

            SectionHeading("Packaging", "What are you sending?")

            Spacer(12)

            Card(
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.open_package),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(18.dp)
                            .width(1.dp)
                            .background(colorGreyText)
                    )

                    Text(text = "Box", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = colorGreyText
                    )
                }
            }

            Spacer(12)
            SectionHeading("Categories", "What are you sending?")
            Spacer(12)

        }
    }
}

@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    hintText: String = "Enter text"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(colorGreyLight)
    ) {
        Icon(icon, contentDescription = "", tint = colorGreyText)
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(18.dp)
                .width(1.dp)
                .background(colorGreyText)
        )
        MoveTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            hintText = hintText,
            textStyle = hintTextStyle.copy(fontWeight = FontWeight.Medium)
        )
    }
}