package com.app.thirydayssongs.data

import com.app.thirydayssongs.R
import com.app.thirydayssongs.model.Song

object SongRepository {
    val allSongs= listOf<Song>(
        Song(
            title = R.string.song_1,
            genre = R.string.genre_1,
            artist = R.string.artist_1,
            releaseDate = 2011,
            image = R.drawable.song1
        ),
        Song(
            title = R.string.song_2,
            genre = R.string.genre_2,
            artist = R.string.artist_2,
            releaseDate = 2016,
            image = R.drawable.song2
        ),
        Song(
            title = R.string.song_3,
            genre = R.string.genre_3,
            artist = R.string.artist_3,
            releaseDate = 2019,
            image = R.drawable.song3
        ),
        Song(
            title = R.string.song_4,
            genre = R.string.genre_4,
            artist = R.string.artist_4,
            releaseDate = 2012,
            image = R.drawable.song4
        ),
        Song(
            title = R.string.song_5,
            genre = R.string.genre_5,
            artist = R.string.artist_5,
            releaseDate = 2015,
            image = R.drawable.song5
        ),
        Song(
            title = R.string.song_6,
            genre = R.string.genre_6,
            artist = R.string.artist_6,
            releaseDate = 2019,
            image = R.drawable.song6
        ),
        Song(
            title = R.string.song_7,
            genre = R.string.genre_7,
            artist = R.string.artist_7,
            releaseDate = 2017,
            image = R.drawable.song7
        ),
        Song(
            title = R.string.song_8,
            genre = R.string.genre_8,
            artist = R.string.artist_8,
            releaseDate = 2022,
            image = R.drawable.song8
        ),
        Song(
            title = R.string.song_9,
            genre = R.string.genre_9,
            artist = R.string.artist_9,
            releaseDate = 2014,
            image = R.drawable.song9
        ),
        Song(
            title = R.string.song_10,
            genre = R.string.genre_10,
            artist = R.string.artist_10,
            releaseDate = 2015,
            image = R.drawable.song10
        )
    )
}