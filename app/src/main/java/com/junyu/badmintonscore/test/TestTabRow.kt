package com.junyu.badmintonscore.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Name TestTabRow
 * @Descript TODO
 * @CreateTime 2023/6/2 12:03
 * @Created by Administrator
 */
@Composable
fun testTabRow(){
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
    val selectedTabIndex = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            backgroundColor = Color.LightGray,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = { selectedTabIndex.value = index }
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        // Content for the selected tab
        when (selectedTabIndex.value) {
            0 -> BasicTextField(
                value = "Content for Tab 1",
                onValueChange = {},
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
            1 -> BasicTextField(
                value = "Content for Tab 2",
                onValueChange = {},
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
            2 -> BasicTextField(
                value = "Content for Tab 3",
                onValueChange = {},
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }

    }



}