package com.junyu.badmintonscore.test

import android.print.PrintAttributes.Margins
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junyu.badmintonscore.R
import com.junyu.badmintonscore.utils.ColorUtils

/**
 * @Name MediaTable
 * @Descript TODO
 * @CreateTime 2023/5/31 17:55
 * @Created by Administrator
 */

const val MSG = "MediaTable"


@Composable
fun MediaTable(contentPadding: PaddingValues){
    val data = listOf(
        "Item 1", "Item 2", "Item 3", "Item 4",
        "Item 5", "Item 6", "Item 7", "Item 8",
        "Item 9", "Item 10", "Item 11", "Item 12",
    )

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
//            .background(ColorUtils.hexToColor("#FF00FF"))) {
            .background(ColorUtils.hexToColor("#F7FCFF"))) {
        
        Box(
            Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White)
        ) {

            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = null, modifier = Modifier
                    .padding(start = 16.dp)
                    .height(24.dp)
                    .width(24.dp)
                    .align(CenterStart)
                    )
            Text(text = "xxxxxxx" ,modifier = Modifier.align(Center), style = TextStyle(fontSize = 20.sp, color = ColorUtils.hexToColor("#00050B")))
        }

        CreateGrid(data,"测试1")
        CreateGrid(data,"测试2")
        CreateGrid(data,"测试3")

//        LazyVerticalGrid(
//            columns = GridCells.Fixed(4)
//            , modifier = Modifier.padding(16.dp)
//        ) {
//            items(data.size) { index ->
//                Text(text = data[index])
//            }
//        }

    }

}

/*
* 创建一个带header的列表
* */
@Composable
fun CreateGrid(data:List<String>,title:String){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(ColorUtils.hexToColorWithAlpha("#2DBEFE", 0.25f))){
            Text(
                text = title,
                modifier = Modifier.align(Center),
                style = TextStyle(fontSize = 20.sp, color = ColorUtils.hexToColor("#00050B"))
            )
        }
        Box(modifier = Modifier.fillMaxWidth().background(color = Color.White , shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))) {
            val rowNum = data.size/4
            Column(modifier = Modifier.fillMaxWidth()) {
                for (i in 0 until rowNum){
                    Log.i(MSG,"i:${i}")
                    Row(modifier = Modifier
                        .height(56.dp).fillMaxWidth()) {
                        for (n in 1..4){
                            Log.i(MSG,"n:${n}")
                            val turePosition = if (i==0)
                            {
                                n
                            }else{
                                n+(i*4)
                            }
                            Log.i(MSG,"turePosition:${turePosition-1}")

                            val itemPosition = data[turePosition-1]

                            val itemModifier = if (i==rowNum-1&&n==1){
                                Modifier.fillMaxHeight().weight(1f).background(ColorUtils.hexToColor("#F2F3F5"),
                                    shape = RoundedCornerShape(bottomStart = 16.dp))
                            }else if(n%2==0){
                                Modifier.fillMaxHeight().weight(1f)
                            }else{
                                Modifier.fillMaxHeight().weight(1f).background(ColorUtils.hexToColor("#F2F3F5"))
                            }
                            Box(modifier = itemModifier) {
                                Text(text = itemPosition, modifier = Modifier.align(Center))
                            }
                        }
                    }
                    if (i!=rowNum-1){
                        Divider(color = ColorUtils.hexToColorWithAlpha("#010101",0.3f), thickness = 0.5.dp)
                    }
                }
            }
        }
    }


}