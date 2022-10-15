package com.app.gallery_assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.gallery_assignment.ui.theme.Gallery_AssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gallery_AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryApp()
                }
            }
        }
    }
}

data class ArtWorks(val imageId:Int,val artistName:String,val artYear:String,val artDescription:String)

@Composable
fun GalleryApp() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ){
        val allArtWorks:MutableList<ArtWorks> = mutableListOf()
        allArtWorks.add(ArtWorks(R.drawable.art1,"Vincent","(2001)","The great mona lisa art work"))
        allArtWorks.add(ArtWorks(R.drawable.art2,"Another One","(2000)","Another one art work"))
        allArtWorks.add(ArtWorks(R.drawable.art3,"New One","(1998)","The last one by another artist"))

        val context= LocalContext.current
        val activeColor= ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700))
        val inActiveColor= ButtonDefaults.buttonColors(containerColor = Color.DarkGray)

        var pageIndex by remember {
            mutableStateOf(0)
        }

        val currentArtWork= getCurrentArtwork(pageIndex,allArtWorks)

        Spacer(modifier = Modifier.height(20.dp))
        GalleryActiveImage(
            imageId = currentArtWork.imageId,
            contentDescription = currentArtWork.artDescription,
            modifier = Modifier
                .height(450.dp)
                .border(2.dp, Color.DarkGray, RectangleShape)
                .fillMaxWidth()
                .padding(15.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TitleAndSubtitleShow(
            title = currentArtWork.artDescription,
            artistName = currentArtWork.artistName,
            year = currentArtWork.artYear,
            modifier = Modifier
                .shadow(5.dp, RectangleShape, true)
                .fillMaxWidth()
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ActionButtonsRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(1f),
            prevColor = if(pageIndex>0) activeColor else inActiveColor,
            nextColor = if(pageIndex==2) inActiveColor else activeColor,
            onNextClicked = {
                if(pageIndex!=2){
                    pageIndex++
                }else{
                    Toast.makeText(context,"You are at the end of all artworks",Toast.LENGTH_SHORT).show()
                }
            },
            onPreviousClicked = {
                if(pageIndex!=0){
                    pageIndex--
                }else{
                    Toast.makeText(context,"You are at the start of all artworks",Toast.LENGTH_SHORT).show()
                }
            },
        )
    }
}

private fun getCurrentArtwork(pageIndex:Int,artworks:MutableList<ArtWorks>):ArtWorks{
    return artworks[pageIndex]
}

@Composable
fun GalleryActiveImage(imageId:Int,contentDescription:String,modifier: Modifier){
    Image(
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun TitleAndSubtitleShow(title:String,artistName:String,year:String,modifier: Modifier){
    Column(
        modifier = modifier
    ){
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light
        )
        Row {
            Text(
                text = artistName,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = year,
                fontWeight = FontWeight.ExtraLight
            )
        }
    }
}

@Composable
fun ActionButtonsRow(
    modifier: Modifier,
    prevColor: ButtonColors,
    nextColor: ButtonColors,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
){
    Row {
        Button(
            onClick = onPreviousClicked,
            modifier = modifier,
            shape = RectangleShape,
            colors = prevColor
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = onNextClicked,
            modifier = modifier,
            shape = RectangleShape,
            colors = nextColor
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Gallery_AssignmentTheme {
        GalleryApp()
    }
}