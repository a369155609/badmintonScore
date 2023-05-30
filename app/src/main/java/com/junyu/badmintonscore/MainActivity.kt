package com.junyu.badmintonscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.junyu.badmintonscore.ui.base.CraneDrawer

import com.junyu.badmintonscore.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

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

    @Preview(showBackground = true)
    @Composable
    private fun testCompose() {
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

        }

    }


}

@Composable
fun Greeting() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.statusBarsPadding(),
        drawerContent = {
            CraneDrawer()
        }
    ) { contentPadding ->
        val scope = rememberCoroutineScope()
        Text(text = "测试", modifier = Modifier.padding(contentPadding))
    }

}

