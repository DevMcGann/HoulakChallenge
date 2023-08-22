package com.gsoft.hourakchallenge.presentation.detailScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navController: NavHostController, id: String?) {
    Text(text = "Artist id: $id")
}