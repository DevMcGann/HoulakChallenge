package com.gsoft.hourakchallenge.presentation.detailScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsoft.hourakchallenge.R
import com.gsoft.hourakchallenge.data.model.Track

@Composable
fun TrackRow(track: Track) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth(fraction = .5f)
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Icon(painterResource(id = R.drawable.ic_music), contentDescription = stringResource(id = R.string.song_icon_description), tint = Color.Green)
        Text(text = track.name, color = Color.White, fontSize = 18.sp,  textAlign = TextAlign.Start)
    }
}