package com.droident.cakelistapp.presentation

sealed class Screen(val route:String){
    object  CakeListScreen: Screen("cake_list")
}
