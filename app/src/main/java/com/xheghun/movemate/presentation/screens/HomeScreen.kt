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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.xheghun.movemate.R
import com.xheghun.movemate.data.model.vehicleList
import com.xheghun.movemate.presentation.custom_views.IconText
import com.xheghun.movemate.presentation.custom_views.SearchTextField
import com.xheghun.movemate.presentation.custom_views.SectionHeading
import com.xheghun.movemate.presentation.ui.HorizontalRule
import com.xheghun.movemate.presentation.ui.Routes
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGray
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange
import com.xheghun.movemate.presentation.ui.theme.colorPurple

@Composable
fun HomeScreen(navController: NavHostController) {

    var currentNavIndex by remember { mutableIntStateOf(0) }

    val navItems = listOf("Home", "Calculate", "Shipment", "Profile")

    Column {
        when (currentNavIndex) {
            1 -> {
                CalculateScreen(navController) {
                    currentNavIndex = 0
                }
            }

            2 -> {
                ShipmentHistoryScreen(navController) {
                    currentNavIndex = 0
                }
            }

            else -> HomeView(navController, Modifier.weight(1f))
        }

        //NAV
        TabRow(selectedTabIndex = currentNavIndex, modifier = Modifier.background(colorOrange)) {
            navItems.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.height(60.dp),
                    unselectedContentColor = colorGreyText,
                    selectedContentColor = colorPurple,
                    selected = currentNavIndex == index,
                    icon = {
                        val iconModifier = Modifier.size(20.dp)

                        when (index) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.home_outline),
                                contentDescription = "",
                                modifier = iconModifier
                            )

                            1 -> Icon(
                                painter = painterResource(id = R.drawable.calculator),
                                contentDescription = "",
                                modifier = iconModifier
                            )

                            2 -> Icon(
                                painter = painterResource(id = R.drawable.recent),
                                contentDescription = "",
                                modifier = iconModifier
                            )

                            3 -> Icon(
                                painter = painterResource(id = R.drawable.person_outline),
                                contentDescription = "",
                                modifier = iconModifier
                            )
                        }
                    },
                    text = {
                        Text(
                            title,
                            fontWeight =
                            if (currentNavIndex == index) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    onClick = { currentNavIndex = index })
            }
        }
    }
}

@Composable
fun ShippingInfo(title: String, text: String, showIcon: Boolean = true, drawableResource: Int = 0) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (showIcon)
            Image(
                painter = painterResource(id = drawableResource),
                contentDescription = "$title icon",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(28.dp)
            )
        if (showIcon)
            Spacer(width = 4)
        Column {
            Text(text = title, fontSize = 12.sp, color = colorGray)
            Text(text = text, fontSize = 13.sp, color = Color.Black, fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun HomeView(navController: NavHostController, modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Column(modifier) {
        //HEADER
        Column(
            Modifier
                .fillMaxWidth()
                .background(bluePrimary)
                .padding(12.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(40.dp)
                        .height(40.dp)
                )
                Spacer(width = 10)
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { }
                ) {
                    IconText(
                        text = "Your location",
                        fontSize = 13,
                        color = colorGreyText,
                        startIcon = ImageVector.vectorResource(R.drawable.send)
                    )
                    IconText(
                        text = "Wertheimer, Illnois",
                        endIcon = Icons.Default.KeyboardArrowDown
                    )
                }
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Outlined.Notifications,
                        contentDescription = "notification",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(15)

            //SEARCH
            SearchTextField(
                enabled = false,
                value = searchText,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.Search.name)
                },
                onValueChange = { searchText = it }
            )

            Spacer(15)

        }

        Spacer(20)

        //BODY
        Column(Modifier.padding(horizontal = 12.dp)) {
            SectionHeading(title = "Tracking", subTitle = "")

            Spacer(15)

            //SHIPPING INFO CARD
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                Modifier
                    .shadow(2.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(10.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Shipment Number",
                            color = colorGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(2)
                        Text(
                            text = "HWBZNN2211S872882",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.tractor),
                        contentDescription = "",
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                    )

                }

                HorizontalRule()

                Row {
                    Column {
                        ShippingInfo(
                            title = "Sender",
                            text = "Atlanta, 5243",
                            drawableResource = R.drawable.open_package
                        )
                        Spacer(10)
                        ShippingInfo(
                            title = "Receiver",
                            text = "Chicago, 6342",
                            drawableResource = R.drawable.open_package
                        )
                    }
                    Box(Modifier.weight(1f))

                    Column {
                        ShippingInfo(
                            showIcon = false,
                            title = "Time",
                            text = "2 days - 3 days",
                            drawableResource = R.drawable.open_package
                        )
                        Spacer(10)
                        ShippingInfo(
                            showIcon = false,
                            title = "Status",
                            text = "Waiting to collect",
                            drawableResource = R.drawable.open_package
                        )
                    }
                }

                HorizontalRule()

                IconText(
                    text = "Add Stop",
                    fontWeight = FontWeight.Bold,
                    startIcon = Icons.Default.Add,
                    color = colorOrange,
                    modifier = Modifier
                        .clickable { }
                        .padding(vertical = 10.dp)
                )
            }

            Spacer(12)

            SectionHeading(title = "Available Vehicles", subTitle = "")

            Spacer(12)

            LazyRow() {
                items(vehicleList()) {

                    Box(
                        Modifier
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .width(120.dp)
                            .height(138.dp)
                    ) {

                        Column {
                            Text(
                                it.name,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .padding(horizontal = 10.dp)
                            )
                            Text(
                                it.remark,
                                fontSize = 12.sp,
                                color = colorGreyText,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.shipping
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = "item image",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(80.dp)
                        )
                    }
                }
            }
        }

    }
}