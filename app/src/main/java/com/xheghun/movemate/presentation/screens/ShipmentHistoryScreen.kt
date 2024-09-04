package com.xheghun.movemate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.xheghun.movemate.R
import com.xheghun.movemate.data.model.Shipment
import com.xheghun.movemate.data.model.Status
import com.xheghun.movemate.data.model.dummyShipments
import com.xheghun.movemate.data.model.getStatusItemCount
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreen
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange
import com.xheghun.movemate.presentation.ui.theme.colorPurple
import com.xheghun.movemate.presentation.ui.theme.smallTextGrey

@Composable
fun ShipmentHistoryScreen(navController: NavHostController) {
    val tabItems = listOf("All", "Completed", "In progress", "Pending", "Loading")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var shipments: List<Shipment> by remember {
        mutableStateOf(dummyShipments())
    }

    Column(Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .background(bluePrimary)
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Shipment History",
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
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = bluePrimary
        ) {
            tabItems.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = {
                        selectedTabIndex = index
                        //TODO("Find a more efficient approach")
                        if (index > 0) {
                            shipments = dummyShipments().filter {
                                it.status.name.lowercase().replace("_", " ")
                                    .capitalize() == tabItems[selectedTabIndex]
                            }
                        } else {
                            shipments = dummyShipments()
                        }
                    }) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else colorPurple,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Spacer(width = 4)
                        Text(
                            text = "${getStatusItemCount(title)}",
                            fontSize = 10.sp,
                            color = if (selectedTabIndex == index) Color.White else colorPurple,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(50.dp)
                                )
                                .background(if (selectedTabIndex == index) colorOrange else colorPurple)
                                .padding(horizontal = 8.dp)

                        )
                    }
                }
            }
        }

        Spacer(10)

        Text(
            text = "Shipments",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(10)

        //SHIPPING LIST
        LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(shipments) {
                Card(
                    Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            Modifier
                                .padding(10.dp)
                                .weight(3f)
                        ) {

                            StatusIndicator(it.status.name)

                            Spacer(4)

                            Text(text = "Arriving Today!", fontWeight = FontWeight.Bold)
                            Text(
                                text = "Your delivery ${it.shipmentNumber} from ${it.sender}, is arriving today!",
                                style = smallTextGrey,
                                fontSize = 13.sp
                            )
                            Spacer(5)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "$220 USD",
                                    style = smallTextGrey,
                                    color = colorPurple,
                                    fontWeight = FontWeight.Medium
                                )
                                Box(
                                    Modifier
                                        .padding(horizontal = 4.dp)
                                        .clip(CircleShape)
                                        .background(colorGreyText)
                                        .padding(2.dp)
                                )
                                Text(
                                    text = "Sep 20, 2023",
                                    style = smallTextGrey,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.tractor),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatusIndicator(statusText: String) {
    val status = statusText.lowercase().replace("_", "-")

    val color: Color = when (status) {
        "in-progress" -> colorGreen
        "pending" -> colorOrange
        else -> bluePrimary
    }

    val icon: ImageVector = when (status) {
        "in-progress" -> Icons.Filled.Refresh
        "pending" -> Icons.Filled.Refresh
        else -> Icons.Filled.Info
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(colorGreyLight)
            .padding(horizontal = 5.dp)
    ) {
        Icon(icon, contentDescription = "", tint = color, modifier = Modifier.size(12.dp))
        Spacer(width = 4)
        Text(status, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = color)
    }
}