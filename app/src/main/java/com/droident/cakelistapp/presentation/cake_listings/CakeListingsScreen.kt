package com.droident.cakelistapp.presentation.cake_listings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.droident.cakelistapp.domain.model.Cake
import com.droident.cakelistapp.presentation.cake_listings.component.CakeListItem
import com.droident.cakelistapp.presentation.cake_listings.component.CompleteDialogContent
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun CakeListingsScreen(
    navController: NavController,
    viewModel: CakeListingsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)


    // Dialog state Manager
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val cakeState: MutableState<Cake> = remember {
        mutableStateOf(Cake(
            title = "",
            desc = "",
            image = "",
        ))
    }

    // Code to Show and Dismiss Dialog
    if (dialogState.value) {
        Dialog(onDismissRequest = { dialogState.value = false }, content = {
            CompleteDialogContent(cakeState.value.title, dialogState) {
                BodyContent(cakeState)
            }
        }, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false))

    }


    Scaffold(topBar = {
        TopAppBar {
            Text(text = "CakeApp")
        }
    }) {

        Box(modifier = Modifier.fillMaxSize()) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(CakeListingsEvent.Refresh)
                },
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = Color.Green,
                        contentColor = Color.DarkGray)
                },

                ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.cakes) { cake ->
                        CakeListItem(cake = cake, onItemClick = {
                            cakeState.value = cake
                            dialogState.value = true

                        })


                        Divider()
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center))
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun BodyContent(cake: MutableState<Cake>) {
    Text(text = cake.value.desc, fontSize = 22.sp)
}


