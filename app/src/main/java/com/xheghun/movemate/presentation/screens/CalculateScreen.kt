package com.xheghun.movemate.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xheghun.movemate.R
import com.xheghun.movemate.presentation.custom_views.MoveTextField
import com.xheghun.movemate.presentation.custom_views.SectionHeading
import com.xheghun.movemate.presentation.ui.Routes
import com.xheghun.movemate.presentation.ui.Spacer
import com.xheghun.movemate.presentation.ui.theme.bluePrimary
import com.xheghun.movemate.presentation.ui.theme.colorGreyLight
import com.xheghun.movemate.presentation.ui.theme.colorGreyText
import com.xheghun.movemate.presentation.ui.theme.colorOrange
import com.xheghun.movemate.presentation.ui.theme.hintTextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculateScreen(
    navController: NavController, onBackPressed: () -> Unit = { navController.popBackStack() }
) {
    val buttonScale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    val textFieldOffset = remember { Animatable(100f) }
    val textFieldOpacity = remember { Animatable(0f) }

    val triggerBounce: (() -> Unit) -> Unit = { onComplete ->
        scope.launch {
            // Animate to a smaller scale (bouncing down)
            buttonScale.animateTo(
                targetValue = 0.8f,
                animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
            )

            // Settle back to normal size
            buttonScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing)
            )

            onComplete.invoke()
        }
    }

    val categoryOption =
        listOf("Document", "Glass", "Liquid", "Food", "Electronics", "Product", "Others")
    var selectedCategory by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scope.launch {
            textFieldOffset.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            )

            textFieldOpacity.animateTo(
                targetValue = 1f, animationSpec = tween(durationMillis = 400)
            )
        }
    }

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
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )

            Icon(Icons.Filled.KeyboardArrowLeft,
                contentDescription = "nav back",
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onBackPressed() }
                    .padding(6.dp)
                    .align(Alignment.CenterStart))
        }


        //BODY
        Column(Modifier.padding(12.dp)) {
            //FORM
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                SectionHeading("Destination", "")

                Spacer(8)
                Column(
                    Modifier
                        .shadow(0.5.dp, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    IconTextField(
                        modifier = Modifier
                            .offset(x = textFieldOffset.value.dp)
                            .alpha(textFieldOpacity.value),
                        value = "",
                        onValueChange = {},
                        hintText = "Sender Location",
                        icon = ImageVector.vectorResource(R.drawable.upload)
                    )
                    IconTextField(
                        modifier = Modifier
                            .offset(x = textFieldOffset.value.dp)
                            .alpha(textFieldOpacity.value),
                        value = "",
                        onValueChange = {},
                        hintText = "Receiver Location",
                        icon = ImageVector.vectorResource(R.drawable.download)
                    )
                    IconTextField(
                        modifier = Modifier
                            .offset(x = textFieldOffset.value.dp)
                            .alpha(textFieldOpacity.value),
                        value = "",
                        onValueChange = {},
                        hintText = "Approx Weight",
                        icon = ImageVector.vectorResource(R.drawable.hourglass_line)
                    )
                }
                Spacer(12)

                SectionHeading("Packaging", "What are you sending?")

                Spacer(12)

                Column(
                    Modifier
                        .shadow(0.5.dp, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
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

                        Text(
                            text = "Box",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
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

                FlowRow {
                    categoryOption.forEachIndexed { index, text ->

                        var optionsVisible by remember { mutableStateOf(false) }

                        val animatedProgress by animateFloatAsState(
                            targetValue = if (optionsVisible) 1f else 0f,
                            animationSpec = tween(durationMillis = 200, delayMillis = index * 50)
                        )

                        LaunchedEffect(Unit) {
                            delay(50L) // Small delay to stagger the animations
                            optionsVisible = true
                        }

                        AssistChip(colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (selectedCategory == text) Color.Black else Color.White
                        ), onClick = { selectedCategory = text }, label = {
                            Text(
                                text,
                                color = if (selectedCategory == text) Color.White else Color.Black
                            )
                        }, leadingIcon = {
                            if (selectedCategory == text) Icon(
                                Icons.Default.Check,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }, modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .graphicsLayer(
                                translationX = (1f - animatedProgress) * 200f,
                                alpha = animatedProgress
                            )
                        )
                    }
                }

            }

            //BUTTON
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(buttonScale.value), onClick = {
                    triggerBounce {
                        navController.navigate(Routes.Total.name)
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = colorOrange)
            ) {
                Text("Calculate", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    hintText: String = "Enter text"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(colorGreyLight)
            .padding(horizontal = 8.dp)
    ) {
        Icon(icon, contentDescription = "", tint = colorGreyText, modifier = Modifier.size(20.dp))
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