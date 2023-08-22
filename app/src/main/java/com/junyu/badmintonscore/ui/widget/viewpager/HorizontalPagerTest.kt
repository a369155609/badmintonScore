package com.junyu.badmintonscore.ui.widget.viewpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * @Name HorizontalPagerTest
 * @Descript TODO
 * @CreateTime 2023/8/22 10:31
 * @Created by Administrator
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerTest() {

    val tabItems = listOf("Home", "Search", "Profile")

    var selectedTabIndex = remember { MutableStateFlow(0) }
    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(selectedTabIndex) {
        selectedTabIndex.collect {

        }
    }

    val count = selectedTabIndex.collectAsState()

    val scope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            state = pagerState,
            pageCount = tabItems.size,
            modifier = Modifier.weight(1f)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        translationX = with(LocalDensity.current) { (page - pagerState.currentPage) * -56.dp.toPx() }
                    )
            ) {
                // 在每个页面中放置内容
                Text(text = tabItems[page], modifier = Modifier.fillMaxSize())
            }
        }

        // 底部导航栏
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .pointerInput(Unit) {
//                        detectTransformGestures { _, _, _, _, _, _ ->
//                            // 处理滑动事件
//                            val deltaX = -delta
//                            pagerState.scrollBy(deltaX / 56.dp.toPx().toInt())
//                        }
                }
        ) {
            tabItems.forEachIndexed { index, title ->
                BottomNavigationItem(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        selectedTabIndex.value = index
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    label = { Text(text = title) }, icon = {}
                )
            }
        }
    }
}
