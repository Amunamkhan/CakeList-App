package com.droident.cakelistapp.presentation.cake_listings

import com.droident.cakelistapp.domain.model.Cake


data class CakeListingsState(
    val isLoading:Boolean=false,
    val cakes:List<Cake> = emptyList(),
    val error:String="",
    val isRefreshing: Boolean=false
)
