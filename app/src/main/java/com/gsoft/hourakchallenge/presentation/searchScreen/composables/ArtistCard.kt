package com.gsoft.hourakchallenge.presentation.searchScreen.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gsoft.hourakchallenge.R
import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.Image

@Composable
fun ArtistCard(
    artist: Artist,
    onClick : () -> Unit
) {
    Card(
        modifier = Modifier.background(Color.Black)
            .padding(vertical = 18.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Green),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(100.dp)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clickable { onClick() },
        ) {
            if (artist.images.isNotEmpty()) {
                AsyncImage(
                    model = artist.images[0].url,
                    contentDescription = stringResource(id = R.string.artist_image_description),
                    placeholder = painterResource(id = R.drawable.spoty),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(end = 25.dp)
                        .clip(CircleShape)
                )
            }
            Text(text = artist.name, color = Color.Green, fontSize = 25.sp)
        }
    }
}


@Preview(showBackground = false)
@Composable
fun PreviewArtistCard() {
    val genres = listOf("Rock", "Pop", "Jazz", "Electronic")
    val images = listOf<Image>(Image(url = "url", width = 100, height = 100))
    ArtistCard(
        artist = Artist(genres,"1", images = images,"Pink Floyd", popularity = 100, ),
        onClick = {}
    )
}