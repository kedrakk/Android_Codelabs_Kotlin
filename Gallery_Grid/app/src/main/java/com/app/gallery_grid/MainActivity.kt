package com.app.gallery_grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.gallery_grid.model.Topic
import com.app.gallery_grid.ui.theme.Gallery_GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gallery_GridTheme {
                GalleryGridApp()
            }
        }
    }
}

@Composable
fun GalleryGridApp() {
    val modifier=Modifier.fillMaxSize()
    Box(modifier = modifier) {
        GalleryGridView(DataSource.topics, modifier = modifier)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryGridView(galleryList:List<Topic>,modifier:Modifier){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        content = {
            items(galleryList.size) { index ->
                Surface(
                    modifier=Modifier.padding(vertical = 3.dp, horizontal = 3.dp)
                ) {
                    GalleryGridItem(galleryItem = galleryList[index],modifier=modifier)
                }
            }
        }
    )
}

@Composable
fun GalleryGridItem(galleryItem:Topic,modifier: Modifier){
    Card(
        elevation = 4.dp,
        backgroundColor = Color.White
    ){
        Row{
            Image(
                painter = painterResource(id = galleryItem.imageResource),
                contentDescription = stringResource(
                    id = galleryItem.title
                ),
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp),
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(id = galleryItem.title)
                )
                Row{
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_ac_unit_24),
                        contentDescription = null
                    )
                    Text(
                        text = galleryItem.resCount.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Gallery_GridTheme {
        GalleryGridItem(DataSource.topics.first(), modifier = Modifier.padding(8.dp))
    }
}