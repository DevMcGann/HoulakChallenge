package com.gsoft.hourakchallenge.presentation.searchScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gsoft.hourakchallenge.R
import com.gsoft.hourakchallenge.data.model.Artist

@Composable
fun ArtistCard(artist: Artist, onClick : () -> Unit ) {
    Row (

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth().height(100.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable { onClick()  },
        ){
        if(artist.images.isNotEmpty()){
            AsyncImage(
                model = artist.images[0].url,
                contentDescription = "artist image",
                placeholder = painterResource(id = R.drawable.spoty),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp)
                    .padding(end = 25.dp)
            )
        }
        Text(text = artist.name, color = Color.Green, fontSize = 25.sp)
    }
}