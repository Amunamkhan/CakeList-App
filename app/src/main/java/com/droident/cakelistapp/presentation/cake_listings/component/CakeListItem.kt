package com.droident.cakelistapp.presentation.cake_listings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.droident.cakelistapp.R
import com.droident.cakelistapp.domain.model.Cake

@Composable
fun CakeListItem(cake: Cake,
                 onItemClick:(Cake)->Unit
                 ) {
             
             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .clickable {
                         onItemClick(cake)
                     }
                     .padding(20.dp),
                 verticalArrangement = Arrangement.SpaceBetween
             ) {
                 AsyncImage(
                     model = ImageRequest.Builder(LocalContext.current)
                         .data(cake.image)
                         .crossfade(true)
                         .build(),
                     placeholder = painterResource(R.drawable.ic_launcher_background),
                     //Todo need to add proper error image in case of error
                     error = painterResource(R.drawable.ic_launcher_background),
                     contentDescription = cake.desc,
                     contentScale = ContentScale.FillBounds,
                     modifier = Modifier.fillMaxWidth().height(160.dp)
                 )
                 Spacer(modifier = Modifier.height(2.dp))
                 Text(text = cake.title,
                      style = MaterialTheme.typography.body1,
                     overflow = TextOverflow.Ellipsis ,
                     textAlign = TextAlign.Start,
                 )
             }
}