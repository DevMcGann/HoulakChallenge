package com.gsoft.hourakchallenge.presentation.splashScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.hourakchallenge.domain.usecase.getTokenUsecase
import com.gsoft.hourakchallenge.util.Contants.KEY_SHARED_PREFERENCE
import com.gsoft.hourakchallenge.util.SharePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = "",
    val isAuth: Boolean = false
)

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUsecase: getTokenUsecase,
    private val sharePreferencesManager: SharePreferencesManager
) : ViewModel() {

    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state


    init {
        getToken()
    }


    fun getToken() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                 val token = getTokenUsecase.invoke()
                if(token != null){
                    sharePreferencesManager.setString(KEY_SHARED_PREFERENCE, token)
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isAuth = true)
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(message = e.message.toString())
            }
        }

    }


}