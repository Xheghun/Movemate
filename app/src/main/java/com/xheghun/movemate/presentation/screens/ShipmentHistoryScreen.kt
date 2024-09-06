package com.xheghun.movemate.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.xheghun.movemate.R
import com.xheghun.movemate.data.model.Shipment
import com.xheghun.movemate.data.model.dummyShipments
import com.xheghun.movemate.data.model.getStatusItemCount
import com.xheghun.movemate.presentation.custom_views.SectionHeading
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreen
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange
import com.xheghun.movemate.presentation.ui.theme.colorPurple
import com.xheghun.movemate.presentation.ui.theme.smallTextGrey
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShipmentHistoryScreen(
    navController: NavHostController,
    onBackPressed: () -> Unit = { navController.popBackStack() }
) {

    val scope = rememberCoroutineScope()

    val tabItems = listOf("All", "Completed", "In progress", "Pending", "Loading")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var isLeftSwipe by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(onDelta = { delta ->
        isLeftSwipe = delta > 0
    })

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val tabOffsetX = remember { Animatable(screenWidth.toFloat()) }
    val tabOffsetY = remember { Animatable(-200f) }

    var shipmentsListVisibleState by remember { mutableStateOf(false) }

    var shipments: List<Shipment> by remember {
        mutableStateOf(dummyShipments())
    }

    val filterShipment: (Int) -> Unit = { index ->
        //TODO("Find a more efficient approach")
        shipments = if (index > 0) {
            dummyShipments().filter {
                it.status.name.lowercase().replace("_", " ")
                    .capitalize() == tabItems[selectedTabIndex]
            }
        } else {
            dummyShipments()
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            tabOffsetX.animateTo(
                targetValue = 0f, // Move into the screen
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        }

        scope.launch {
            tabOffsetY.animateTo(
                targetValue = 0f, // Move into the screen
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        //APP BAR
        Box(
            Modifier
                .background(bluePrimary)
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Shipment history",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "nav back",
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onBackPressed() }
                    .padding(6.dp)
                    .align(Alignment.CenterStart)
            )
        }

        //TABS
        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = selectedTabIndex,
            containerColor = bluePrimary,
            indicator = { tabPositions ->
                val selectedTabPosition = tabPositions[selectedTabIndex]

                Box(
                    Modifier
                        .tabIndicatorOffset(selectedTabPosition) // Aligns with selected tab
                        .fillMaxWidth()
                        .height(4.dp)  // Height of the indicator
                        .background(colorOrange) // Custom indicator color
                )
            }
        ) {
            tabItems.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    modifier = Modifier.padding(end = 10.dp).offset(x = tabOffsetX.value.dp, y = tabOffsetY.value.dp),
                    onClick = {
                        if (selectedTabIndex != index) {
                            shipmentsListVisibleState = false
                        }
                        selectedTabIndex = index
                        filterShipment(index)

                    }) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
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
                            color = if (selectedTabIndex == index) Color.White else bluePrimary,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(50.dp)
                                )
                                .background(if (selectedTabIndex == index) colorOrange else colorPurple)
                                .padding(horizontal = 12.dp)

                        )
                    }
                }
            }
        }

        //BODY
        Column(Modifier
            .padding(12.dp)
            .draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStarted = {},
                onDragStopped = {
                    //TODO("a better approach can be implemented as this method would cause a recomposition even when we're at the last item")
                    if (isLeftSwipe) {
                        selectedTabIndex = (selectedTabIndex - 1).coerceAtLeast(0)
                        filterShipment(selectedTabIndex)
                    } else {
                        selectedTabIndex = (selectedTabIndex + 1).coerceAtMost(4)
                        filterShipment(selectedTabIndex)
                    }

                }
            )) {
            SectionHeading(title = "Shipments", subTitle = "")

            Spacer(10)

            //SHIPPING LIST
            LazyColumn() {
                itemsIndexed(shipments) { index, item ->

                    val animatedProgress by animateFloatAsState(
                        targetValue = if (shipmentsListVisibleState) 1f else 0f,
                        animationSpec = tween(durationMillis = 300, delayMillis = index * 50)
                    )

                    LaunchedEffect(selectedTabIndex) {
                        delay(300L) // Small delay to stagger the animations
                        shipmentsListVisibleState = true
                    }

                    ShipmentItem(
                        item, Modifier.graphicsLayer(
                            translationY = (1f - animatedProgress) * 200f,
                            alpha = animatedProgress
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun ShipmentItem(item: Shipment, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .padding(vertical = 6.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .weight(3f)
        ) {

            StatusIndicator(item.status.name)

            Spacer(4)

            Text(text = "Arriving Today!", fontWeight = FontWeight.Bold)
            Text(
                text = "Your delivery ${item.shipmentNumber} from ${item.sender}, is arriving today!",
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
            painter = painterResource(id = R.drawable.move_box),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .weight(1f)
        )
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
        "in-progress" -> ImageVector.vectorResource(R.drawable.sync)
        "pending" -> ImageVector.vectorResource(R.drawable.recent)
        else -> ImageVector.vectorResource(R.drawable.time_quarter)
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