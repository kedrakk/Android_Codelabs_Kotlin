package com.app.thirydayssongs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.thirydayssongs.data.SongRepository
import com.app.thirydayssongs.model.Song
import com.app.thirydayssongs.ui.theme.ThiryDaysSongsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThiryDaysSongsTheme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ThirtyDaysSongsApp()
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysSongsApp() {
    Scaffold() {
        SongList(allSongs = SongRepository.allSongs)
    }
}

@Composable
fun SongList(allSongs:List<Song>){
    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ){
        items(allSongs){
            SongItem(song = it, index = allSongs.indexOf(it))
        }
    }
}

@Composable
fun SongItem(song: Song,index:Int){
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Column() {
            Row() {
                SongImage(image = song.image, contentDes = song.title)
                SongDescription(title = song.title, releaseDate = song.releaseDate,index=index+1)
            }
            SongAdditionalInfo(label = R.string.artist, value =song.artist )
            SongAdditionalInfo(label = R.string.genre, value =song.genre )
            Row {
                Icon(Icons.Filled.AccountBox,contentDescription = null)
                Icon(Icons.Filled.AccountBox,contentDescription = null)
                Icon(Icons.Filled.AccountBox,contentDescription = null)
            }
        }
    }
}

@Composable
fun SongImage(@DrawableRes image:Int,@StringRes contentDes:Int){
    Image(
        painter = painterResource(id = image), 
        contentDescription = stringResource(id = contentDes)
    )
}

@Composable
fun SongDescription(
    @StringRes title:Int,
    releaseDate:Int,
    index: Int
){
    Column() {
        Text(text = "Day $index")
        Text(text = stringResource(id = title))
        Text(text = stringResource(id = R.string.release_date,releaseDate.toString()))
    }
}

@Composable
fun SongAdditionalInfo(label:Int,value:Int){
    Row() {
        Text(text = stringResource(id = label))
        Text(text = stringResource(id = value))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThiryDaysSongsTheme {
        ThirtyDaysSongsApp()
    }
}