package com.junyu.badmintonscore.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * @Name PressIconButton
 * @Descript TODO
 * @CreateTime 2023/8/16 10:35
 * @Created by Administrator
 */
@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val isPressed = interactionSource.collectIsPressedAsState().value
//    val test: State<Boolean> by lazy { interactionSource.collectIsPressedAsState() }

    Button(onClick = onClick, modifier = modifier,
        interactionSource = interactionSource) {
        AnimatedVisibility(visible = isPressed) {
//            if (isPressed) {
//                Row {
//                    icon()
//                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                }
//            }
            Row {
                icon()
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }

        }
        text()
    }
}