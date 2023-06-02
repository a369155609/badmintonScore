package com.junyu.badmintonscore.utils

import androidx.compose.ui.graphics.Color

/**
 * @Name ColorUtils
 * @Descript TODO
 * @CreateTime 2023/5/31 10:49
 * @Created by Administrator
 */
object ColorUtils {

    fun hexToColorWithAlpha(hexColor: String, alpha: Float): Color {
        val cleanHexColor = if (hexColor.startsWith("#")) hexColor.substring(1) else hexColor
        val colorInt = cleanHexColor.toLongOrNull(16) ?: throw IllegalArgumentException("Invalid hex color: $hexColor")
        val alphaInt = (alpha.coerceIn(0f, 1f) * 255).toInt()
        return Color(alphaInt shl 24 or colorInt.toInt())
    }


    fun hexToColor(hexColor: String): Color {
        val cleanHexColor = if (hexColor.startsWith("#")) hexColor.substring(1) else hexColor
        val colorInt = cleanHexColor.toLongOrNull(16) ?: throw IllegalArgumentException("Invalid hex color: $hexColor")
        return Color(colorInt or 0xFF000000.toLong())
    }

}