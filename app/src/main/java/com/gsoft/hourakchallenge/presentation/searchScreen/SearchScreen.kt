package com.gsoft.hourakchallenge.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gsoft.hourakchallenge.presentation.searchScreen.composables.ArtistCard
import com.gsoft.hourakchallenge.presentation.searchScreen.composables.Message
import com.gsoft.hourakchallenge.presentation.searchScreen.composables.SearchComponent


@Composable
fun SearchScreen(navController: NavHostController) {

    val mainViewModel = hiltViewModel<SearchViewModel>()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (searchComponent, searchResult, loading, error, welcome) = createRefs()

        Box(modifier = Modifier.constrainAs(searchComponent) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            SearchComponent(fetchData = { query -> mainViewModel.fetchData(query) })
        }

        if (mainViewModel.feedState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(loading) {
                        top.linkTo(searchComponent.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .size(100.dp),
                color = Color.Green
            )
        }


        if (mainViewModel.feedState.feedDate.isEmpty() && mainViewModel.feedState.showMessage) {
            Box(modifier = Modifier
                .constrainAs(welcome) {
                    top.linkTo(searchComponent.bottom, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Message(message = mainViewModel.feedState.message, color = Color.Green)
            }
        }

        if (mainViewModel.feedState.feedDate.isNotEmpty()) {
            LazyColumn(
                content = {
                    items(mainViewModel.feedState.feedDate.size) {
                        ArtistCard(mainViewModel.feedState.feedDate[it], onClick = {
                            navController.navigate("detail/${mainViewModel.feedState.feedDate[it].id}")
                        })
                    }
                },
                modifier = Modifier
                    .constrainAs(searchResult) {
                        top.linkTo(searchComponent.bottom, margin = 50.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 150.dp)
                    }
                    .fillMaxWidth()
                    .height(500.dp)
            )
        }

    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}