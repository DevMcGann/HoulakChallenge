package com.gsoft.hourakchallenge.presentation.detailScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreRow(genre: String) {
    Card(
        modifier = Modifier
            .height(30.dp)
            .padding(horizontal = 5.dp)
            .background(Color.DarkGray)
    ) {
        Text(text = genre, color = Color.Red, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp))
    }
}
