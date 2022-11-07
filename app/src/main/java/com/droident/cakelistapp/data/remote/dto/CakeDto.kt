package com.droident.cakelistapp.data.remote.dto


import com.droident.cakelistapp.domain.model.Cake
import com.google.gson.annotations.SerializedName

data class CakeDto(
    val desc: String,
    val image: String,
    val title: String
)
fun CakeDto.toCake():Cake{
    return Cake(
        desc=desc,
        image=image,
        title=title
    )
}

