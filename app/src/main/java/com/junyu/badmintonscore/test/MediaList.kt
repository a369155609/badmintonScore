package com.junyu.badmintonscore.test

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.junyu.badmintonscore.R
import com.junyu.badmintonscore.data.MediaEntity
import com.junyu.badmintonscore.utils.ColorUtils


/**
 * @Name MediaList
 * @Descript TODO
 * @CreateTime 2023/5/31 10:04
 * @Created by Administrator
 */
@Composable
fun MediaList(contentPadding: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
//            .background(ColorUtils.hexToColor("#FF00FF"))) {
            .background(ColorUtils.hexToColor("#F7FCFF"))) {
        Box(modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
//            .background(color = ColorUtils.hexToColor("#FF2DBEFE"))
            ){
            Image(painter = painterResource(id = R.drawable.img_sensor_top),
                modifier = Modifier
                    .background(color = ColorUtils.hexToColor("#FF2DBEFE"))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentDescription = null,contentScale = ContentScale.FillBounds)
        }
        Text(text = "7 encoders in total",modifier = Modifier.padding(start = 16.dp, top = 28.dp),
            style = TextStyle(fontSize = 20.sp, color = ColorUtils.hexToColor("#00050B")))
        createUiList()
//        Surface(modifier = Modifier
//            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
//            .shadow(4.dp, RoundedCornerShape(16.dp))) {
//
//        }
    }
}

@Composable
fun createUiList(){
    val testList = createFakeData()
    Column(modifier = Modifier
        .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
        .background(color = Color.White)
        .border(
            BorderStroke(0.5.dp, ColorUtils.hexToColorWithAlpha("#010101", 0.3f)),
            shape = RoundedCornerShape(16.dp)
        )
    ){
        testList.forEachIndexed{
                index, mediaEntity ->
            MediaItem(mediaEntity)
            if (index!=testList.size-1){
                Divider(color = ColorUtils.hexToColorWithAlpha("#010101",0.3f), thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun MediaItem(entity: MediaEntity){
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(Color.White)) {
        val (title, context,arrow) = createRefs()

        Text(text = entity.title, modifier = Modifier.constrainAs(title){
             top.linkTo(parent.top,margin = 6.dp)
             start.linkTo(parent.start,margin = 16.dp)
        }, style = TextStyle(fontSize = 14.sp, color = ColorUtils.hexToColor("#00050B")))

        Text(text = entity.context, modifier = Modifier.constrainAs(context){
            top.linkTo(title.bottom,margin = 4.dp)
            start.linkTo(title.start)
        }, style = TextStyle(fontSize = 14.sp, color = ColorUtils.hexToColorWithAlpha("#010101",0.48f)))
        
        Image(painter = painterResource(id = R.drawable.arrow), contentDescription = null, modifier = Modifier.constrainAs(arrow){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end, margin = 16.dp)
        })
    }
}

fun createFakeData():MutableList<MediaEntity>{
    val cacheList = mutableListOf<MediaEntity>()
    for (i in 0..7){
        cacheList.add(MediaEntity("我是标题:${i}","我是内容:${i}"))
    }
    return cacheList
}