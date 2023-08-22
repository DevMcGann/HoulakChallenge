package com.gsoft.hourakchallenge.presentation.searchScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsoft.hourakchallenge.R
import androidx.compose.foundation.Image


@Composable
fun Message(message : String, color : Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.spoty), contentDescription = "logo")
        Text( message, color = color, fontSize = 20.sp ,textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 12.dp))
    }
}