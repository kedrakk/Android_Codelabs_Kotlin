package com.app.lemonsqueezer

import android.gesture.Gesture
import android.os.Bundle
import android.view.GestureDetector
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.lemonsqueezer.ui.theme.LemonSqueezerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonSqueezerApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonSqueezerApp(){
    LemonSqueezerStep(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Composable
fun LemonSqueezerStep(modifier: Modifier){
    var pageIndex by remember {
        mutableStateOf(1)
    }
    val descRes=when(pageIndex){
        1->R.string.step1_instruction
        2->R.string.step2_instruction
        3->R.string.step3_instruction
        else->R.string.step4_instruction
    }
    val imageRes=when(pageIndex){
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        else->R.drawable.lemon_restart
    }
    val contentRes=when(pageIndex){
        1->R.string.pic1_content
        2->R.string.pic2_content
        3->R.string.pic3_content
        else->R.string.pic4_content
    }
    val context= LocalContext.current
    var clickCount=(2..4).random()
    Column(
        modifier
            .wrapContentWidth(
                align = Alignment.CenterHorizontally
            )
    ) {
        Text(text = stringResource(id = descRes))
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = contentRes),
            modifier = Modifier.clickable {
                if(pageIndex==4){
                    pageIndex=1
                }
                else if(pageIndex==1){
                    if(clickCount>0){
                        clickCount--;
                        Toast.makeText(context,"Click $clickCount more times",Toast.LENGTH_SHORT ).show()
                    }
                    else{
                        pageIndex++
                    }
                }else{
                    pageIndex++
                }
            }
        )

    }
}

