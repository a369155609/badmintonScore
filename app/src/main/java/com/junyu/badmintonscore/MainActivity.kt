package com.junyu.badmintonscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.junyu.badmintonscore.ui.base.CraneDrawer

import com.junyu.badmintonscore.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    val labelList = listOf("首页","打球","比赛","我的")

    val iconList = listOf(Icons.Default.Home,Icons.Default.SportsTennis,Icons.Default.Navigation,Icons.Default.Info)

    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Cursive,
                fontFeatureSettings = "liga=1;kern=1"
            )
        ) {
            append("Hello World")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            testCompose()
            Greeting()
        }
    }


    @Composable
    private fun TestCompose(contentPadding: PaddingValues) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {

            Text(text = "测试")

            Box(
                Modifier
                    .align(Alignment.BottomStart)
                    .size(150.dp, 150.dp)
                    .background(Color.Red)
            )

            Column(
                modifier = Modifier
                    .padding()
                    .align(Alignment.Center),
            ) {
                Text(
                    text = "我是测试方法1",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Blue,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
                Text(
                    text = "我是测试方法2",
                )
                Text(text = text)
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
//                    .clip(CircleShape)
                    .background(Color(R.color.purple_200))
                    .offset(x = 20.dp, y = 30.dp)


            ) {
                Icon(
                    imageVector = Icons.Default.SportsTennis,
                    contentDescription = "Favorite",
                )
            }

        }

    }
    @Composable
    fun Greeting() {
        val scaffoldState = rememberScaffoldState()
        var selectIndex = remember{
            mutableStateOf(0)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.statusBarsPadding(),
            drawerContent = {
                CraneDrawer()
            }, bottomBar = {
                CreateBottomBar(selectIndex)
            }
        ) { contentPadding ->
            val scope = rememberCoroutineScope()
//        Text(text = "测试", modifier = Modifier.padding(contentPadding))
//            TestCompose(contentPadding)
            pageChange(contentPadding,selectIndex)
        }

    }
    @Composable
    private fun pageChange(contentPadding: PaddingValues, selectIndex: MutableState<Int>) {
        val count by remember { mutableStateOf(0) }

        when(selectIndex.value){
            0 ->{
                TextPage("首页",contentPadding)
            }
            1 ->{
                TextPage("打球",contentPadding)
            }
            2 ->{
                TextPage("比赛",contentPadding)
            }
            3 ->{
                TextPage("我的",contentPadding)
            }

        }
    }
    @Composable
    private fun TextPage(s: String,contentPadding: PaddingValues,) {
        Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
            Text(text = s,modifier = Modifier.align(Alignment.Center))
        }
    }

    @Composable
    fun CreateBottomBar(selectIndex: MutableState<Int>) {
        BottomAppBar(backgroundColor = colorResource(id = R.color.white)) {
            labelList.forEachIndexed{
                index, item ->
                BottomNavigationItem(label = { Text(text = item)},
                    selected = index == selectIndex.value ,
                    onClick = { selectIndex.value = index }, icon = { Icon(
                        imageVector = iconList[index],
                        contentDescription = null
                    )}, selectedContentColor = colorResource(id = R.color.teal_200), unselectedContentColor = colorResource(id = R.color.purple_200))
            }
            
        }
    }
}



