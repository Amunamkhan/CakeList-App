package com.droident.cakelistapp.presentation.cake_listings


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droident.cakelistapp.common.Resource
import com.droident.cakelistapp.domain.use_case.get_cake.GetCakes

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CakeListingsViewModel @Inject constructor(
    private val getCakeUseCase: GetCakes,
) : ViewModel() {

    private val _state = mutableStateOf(CakeListingsState())
    val state: State<CakeListingsState> = _state

    init {
        getCakes()
    }

    private fun getCakes() {
        getCakeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CakeListingsState(cakes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        CakeListingsState(error = result.message ?: "An Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CakeListingsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CakeListingsEvent) {
        when (event) {
            is CakeListingsEvent.Refresh -> {
                getCakes()
            }
        }
    }

}

