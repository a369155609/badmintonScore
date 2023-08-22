package com.junyu.badmintonscore.ui.widget


import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.junyu.badmintonscore.utils.ColorUtils
import com.junyu.badmintonscore.viewmodel.MainViewModel

/**
 * @Name ScoreWidget
 * @Descript TODO
 * @CreateTime 2023/8/15 10:28
 * @Created by Administrator
 */

const val TAG = "ScoreWidget"


@Composable
fun ScoreWidget(viewModel: MainViewModel) {

    val blueCount = viewModel.blueCount.observeAsState()

    val redCount = viewModel.redCount.observeAsState()

    val blue = ColorUtils.hexToColor("#2a5caa")

    val red = ColorUtils.hexToColor("#f05b72")

    val startColor = ColorUtils.hexToColor("#feeeed")

    val endColor = ColorUtils.hexToColor("#1d953f")

    var lastRecordTime = remember { mutableStateOf(-1L) }

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed = interactionSource.collectIsPressedAsState().value

    val blueIndication = rememberRipple(color = Color.Blue)

    val grayIndication = rememberRipple(bounded = false, color = Color.Gray)

    val interactions = remember { mutableStateListOf<Interaction>() }

    val lastInteraction = when (interactions.lastOrNull()) {
        is DragInteraction.Start -> "Dragged"
        is PressInteraction.Press -> "Pressed"
        else -> "No state"
    }

    val animatedColor = animateColorAsState(
        targetValue = if (isPressed) endColor else startColor
    )

    LaunchedEffect(interactionSource){
        interactionSource.interactions.collect{
                interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }
                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }
                is DragInteraction.Start -> {
                    interactions.add(interaction)
                }
                is DragInteraction.Stop -> {
                    interactions.remove(interaction.start)
                }
                is DragInteraction.Cancel -> {
                    interactions.remove(interaction.start)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PressIconButton(
            onClick = {},
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
            text = { Text("Add to cart") }
        )

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "当前的比分：${blueCount.value}:${redCount.value}",
            style = TextStyle(fontSize = 30.sp, color = ColorUtils.hexToColor("#00050B"))
        )

        Box(modifier = Modifier
            .clickable(interactionSource, null) {
//                Log.i(TAG, "double click start:${lastRecordTime.value}")
                if (lastRecordTime.value == -1L) {
                    lastRecordTime.value = System.currentTimeMillis()
                    return@clickable
                } else if (System.currentTimeMillis() - lastRecordTime.value > 300) {
                    Log.i(
                        TAG,
                        "double click fail${System.currentTimeMillis() - lastRecordTime.value}"
                    )
                    lastRecordTime.value = -1
                    return@clickable
                }
                Log.i(TAG, "double click success")
                viewModel.addBlueCount()
                lastRecordTime.value = -1
            }
            .weight(1f)
            .fillMaxWidth()
            .background(
                color = animatedColor.value,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
            contentAlignment = Alignment.Center) {
            Text(text = "加一分：${blueCount.value}")
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clickable {
                    viewModel.addRedCount()
                }
                .background(
                    color = red,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(text = "加一分")
        }


    }
}
