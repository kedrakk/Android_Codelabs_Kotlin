package com.app.thirydayssongs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.thirydayssongs.data.SongRepository
import com.app.thirydayssongs.model.Song
import com.app.thirydayssongs.ui.theme.Shapes
import com.app.thirydayssongs.ui.theme.ThiryDaysSongsTheme
import com.app.thirydayssongs.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme=true
            ThiryDaysSongsTheme(
                darkTheme = darkTheme
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
    Scaffold(
        topBar = {
            SongAppBar()
        }
    ) {
        SongList(allSongs = SongRepository.allSongs)
    }
}

@Composable
fun SongAppBar(){
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        content = {
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.ic_baseline_music_note_24),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.app_bar_title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.h3
            )
        }
    )
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
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(Shapes.medium)
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                SongImage(image = song.image, contentDes = song.title)
                SongDescription(
                    title = song.title,
                    releaseDate = song.releaseDate,
                    index=index+1,
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    onClick = {
                        expanded=!expanded
                    }
                )
            }
            if (expanded){
                SongInfoAndActions(artist = song.artist, genre = song.genre)
            }
        }
    }
}

@Composable
fun SongInfoAndActions(artist:Int,genre:Int){
    Column() {
        SongAdditionalInfo(label = R.string.artist, value =artist )
        SongAdditionalInfo(label = R.string.genre, value =genre )
        AdditionalButtons()
    }
}

@Composable
fun SongImage(@DrawableRes image:Int,@StringRes contentDes:Int){
    Image(
        painter = painterResource(id = image), 
        contentDescription = stringResource(id = contentDes),
        contentScale = ContentScale.Fit,
        modifier = Modifier.height(120.dp)
    )
}

@Composable
fun SongDescription(
    @StringRes title:Int,
    releaseDate:Int,
    index: Int,
    imageVector: ImageVector,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "Day $index",
                style = Typography.body2.copy(fontWeight = FontWeight.Normal),
            )
            IconButton(onClick = onClick) {
                Icon(imageVector = imageVector, contentDescription = null)
            }
        }
        Text(text = stringResource(id = title), style = Typography.h3)
        Text(
            text = stringResource(id = R.string.release_date,releaseDate.toString()),
            style = Typography.body1.copy(fontWeight = FontWeight.Light,)
        )
    }
}

@Composable
fun SongAdditionalInfo(label:Int,value:Int){
    Row() {
        Text(
            text = stringResource(id = label),
            modifier = Modifier.weight(1f),
            style = Typography.body1.copy(fontWeight = FontWeight.Light)
        )
        Text(
            text = stringResource(id = value),
            modifier = Modifier.weight(3f),
            style = Typography.body1.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun AdditionalButtons(){
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.soundcloud),
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .padding(horizontal = 5.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.youtube),
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .padding(horizontal = 5.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.spotify),
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .padding(horizontal = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThiryDaysSongsTheme(
        darkTheme = false
    ) {
        SongItem(song = SongRepository.allSongs.first(), index = 0)
    }
}