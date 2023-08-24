package com.gsoft.hourakchallenge.presentation.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gsoft.hourakchallenge.presentation.detailScreen.composables.ArtistInfo
import com.gsoft.hourakchallenge.presentation.searchScreen.composables.Message

@Composable
fun DetailScreen(navController: NavHostController, id: String?) {

    val detailViewModel = hiltViewModel<DetailViewModel>()

    LaunchedEffect(key1 = null) {
        if (id != null) {
            detailViewModel.fetchData(id)
        }
    }

    LaunchedEffect(key1 = null) {
        if (id != null) {
            detailViewModel.fetchSongs(id)
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val (artist, loading, message) = createRefs()

        //loading state
        if (detailViewModel.detailState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(loading) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .size(100.dp),
                color = Color.Green
            )
        }


        //message State
        if (detailViewModel.detailState.artistData == null && detailViewModel.detailState.showMessage) {
            Box(modifier = Modifier
                .constrainAs(message) {
                    top.linkTo(parent.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Message(message = detailViewModel.detailState.message, color = Color.Green)
            }
        }

        //artist info
        if (detailViewModel.detailState.artistData != null) {
            Box(modifier = Modifier
                .fillMaxSize()
                .constrainAs(artist) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
                ArtistInfo(artist = detailViewModel.detailState.artistData!!, songs = detailViewModel.detailState.tracks, back = {
                    navController.popBackStack()
                })
            }
        }

    }
}



@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), id = null)
}