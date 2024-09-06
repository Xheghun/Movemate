package com.xheghun.movemate.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import kotlinx.coroutines.launch
import kotlin.math.pow

@Composable
fun TotalScreen(navController: NavController) {

    val buttonScale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    val targetPrice = 1458f

    var animatedPrice by remember { mutableIntStateOf(0) } // Start counting from 0

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

    // Trigger the animation when the composable is first loaded
    LaunchedEffect(targetPrice) {
        animate(
            initialValue = 0f,
            targetValue = targetPrice,
            animationSpec = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        ) { value, _ ->
            animatedPrice = value.toInt()
        }
    }

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
        Text("Total Estimated Amount", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Spacer(5)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "$animatedPrice",
                fontSize = 24.sp,
                color = colorGreen,
                fontWeight = FontWeight.Medium
            )
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
            modifier = Modifier
                .fillMaxWidth()
                .scale(buttonScale.value),
            onClick = {
                triggerBounce {
                    navController.navigate(Routes.Home.name) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorOrange)
        ) {
            Text("Back to home", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}