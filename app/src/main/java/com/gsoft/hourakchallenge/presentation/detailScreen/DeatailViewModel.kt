package com.gsoft.hourakchallenge.presentation.detailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.hourakchallenge.data.model.Artist
import com.gsoft.hourakchallenge.data.model.Track
import com.gsoft.hourakchallenge.domain.usecase.getArtistUsecase
import com.gsoft.hourakchallenge.domain.usecase.getTracksUsecase
import com.gsoft.hourakchallenge.util.MyResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class DetailState(
    val artistData: Artist? = null,
    val tracks : List<Track>? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val showMessage : Boolean = false,
    val message : String = "We cant find the artist"
)

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val usecase: getArtistUsecase,
    private val trackUsecase: getTracksUsecase
) : ViewModel() {

    var detailState by mutableStateOf(DetailState())
        private set

    fun fetchData(id: String) {
        viewModelScope.launch {
            usecase.invoke(id).collect { it ->
                detailState = detailState.copy(
                    isLoading = true,
                    error = false,
                    showMessage = false
                )
                when (it){
                    is MyResource.Success -> {
                        if(it.data == null){
                            detailState = detailState.copy(
                                isLoading = false,
                                error = true,
                                showMessage = true,
                                message = "We cant find the artist"
                            )
                        }
                        it.data?.let {
                            detailState = detailState.copy(
                                isLoading = false,
                                error = false,
                                showMessage = false,
                                artistData = it
                            )
                        }
                    }
                    is MyResource.Failure -> {
                        detailState = detailState.copy(
                            isLoading = false,
                            error = true,
                            showMessage = true,
                            message = it.exception.message.toString()
                        )
                    }
                    else -> {
                        detailState = detailState.copy(
                            isLoading = false,
                            error = false,
                            showMessage = false,
                            message = ""
                        )
                    }
                }
            }
        }
    }

   fun fetchSongs(id: String){
       viewModelScope.launch {
           trackUsecase.invoke(id).collect { it ->
               detailState = detailState.copy(
                   isLoading = true,
                   error = false,
                   showMessage = false
               )
               when (it){
                   is MyResource.Success -> {
                       if(it.data == null){
                           detailState = detailState.copy(
                               isLoading = false,
                               error = true,
                               showMessage = true,
                               message = "We cant find tracks!"
                           )
                       }
                       it.data?.let {
                           var fiveSongs = 0
                           if(it.tracks.size > 4){
                                fiveSongs = 5
                           }else{
                               fiveSongs = it.tracks.size
                           }
                           detailState = detailState.copy(
                               isLoading = false,
                               error = false,
                               showMessage = false,
                               tracks = it.tracks.sortedByDescending { it.popularity }.subList(0,fiveSongs)
                           )
                       }
                   }
                   is MyResource.Failure -> {
                       detailState = detailState.copy(
                           isLoading = false,
                           error = true,
                           showMessage = true,
                           message = it.exception.message.toString()
                       )
                   }
                   else -> {
                       detailState = detailState.copy(
                           isLoading = false,
                           error = false,
                           showMessage = false,
                           message = ""
                       )
                   }
               }
           }
       }
    }



}