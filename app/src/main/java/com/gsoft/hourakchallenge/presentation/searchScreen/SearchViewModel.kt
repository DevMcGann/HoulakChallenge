package com.gsoft.hourakchallenge.presentation.searchScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.domain.usecase.SearchArtistsUsecase
import com.gsoft.hourakchallenge.util.MyResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FeedState(
    val feedDate: List<Artist> = emptyList(),
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val showMessage : Boolean = true,
    val message : String = "Welcome to HourakChallenge"
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val usecase: SearchArtistsUsecase
): ViewModel() {

    var feedState by mutableStateOf(FeedState())
        private set

     fun fetchData(query: String) {
        viewModelScope.launch {
            usecase.invoke(query).collect { it ->
                feedState = feedState.copy(
                    isLoading = true,
                    error = false,
                    showMessage = false
                )
                when (it){
                    is MyResource.Success -> {
                        it.data?.artists?.items?.let {
                            feedState = feedState.copy(
                                isLoading = false,
                                error = false,
                                showMessage = false,
                                feedDate = it.sortedByDescending { it.popularity }
                            )
                        }
                    }
                    is MyResource.Failure -> {
                        feedState = feedState.copy(
                            isLoading = false,
                            error = true,
                            showMessage = true,
                            message = it.exception.message.toString()
                        )
                    }
                    else -> {
                        feedState = feedState.copy(
                            isLoading = false,
                            error = false,
                            showMessage = true,
                            message = "Welcome to HourakChallenge"
                        )
                    }
                }
            }
        }
    }

}