package com.gsoft.hourakchallenge.presentation.detailScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gsoft.hourakchallenge.R
import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.Image
import com.gsoft.hourakchallenge.data.model.Track

@Composable
fun ArtistInfo(
    artist: Artist,
    songs: List<Track>? = null,
    back : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        //artist image
        if(artist.images.isNotEmpty()){
            AsyncImage(
                model = artist.images[0].url,
                contentDescription = stringResource(id = R.string.artist_image_description),
                placeholder = painterResource(id = R.drawable.spoty),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
        }
        //artist name
        Text(
            text = artist.name,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            fontSize = 35.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
        )


        //genres
        if (artist.genres.isNotEmpty()) {
            Text(stringResource(id = R.string.genres), color = Color.Green, fontSize = 25.sp)
            LazyRow(
                content = {
                    items(artist.genres.size) {
                        GenreRow(genre = artist.genres[it])
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center
            )
        }

        //songs
        if (!songs.isNullOrEmpty()) {
            Text(stringResource(id = R.string.top_tracks), color = Color.Green, fontSize = 25.sp, modifier = Modifier.padding(top = 30.dp))
            LazyColumn(
                content = {
                    items(songs.size) {
                        TrackRow(track = songs[it])
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
        }

        //go back
        IconButton(onClick = { back()}) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back_description), tint = Color.Green )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewArtistInfo() {
    val artist = Artist(
        id = "1",
        name = "Creendence Clearwater Revival",
        popularity = 1,
        images = listOf<Image>(Image(url = "url", height = 300, width = 300)),
        genres = listOf("Rock", "Pop", "Country", "BlueGrass"),
    )

    val tracks = listOf<Track>(
        Track("1", "Cotton Fields", 100),
        Track("2", "Have you ever seen the rain", 90),
        Track("3", "Fortunate Son", 100),
        Track("4", "Travelling Band", 100),

    )
    ArtistInfo(artist = artist, songs  = tracks, back = {})
}