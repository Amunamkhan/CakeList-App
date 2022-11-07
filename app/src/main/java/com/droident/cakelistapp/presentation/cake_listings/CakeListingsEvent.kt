package com.droident.cakelistapp.presentation.cake_listings

sealed class CakeListingsEvent {
    object Refresh: CakeListingsEvent()

}