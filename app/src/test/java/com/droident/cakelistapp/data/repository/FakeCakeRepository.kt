package com.droident.cakelistapp.data.repository

import com.droident.cakelistapp.data.remote.dto.CakeDto
import com.droident.cakelistapp.domain.repository.CakeRepository


class FakeCakeRepository : CakeRepository {
    private val cakes = mutableListOf<CakeDto>()
    override suspend fun getCakes(): List<CakeDto> {
        return getCakesList()
    }

    // This method is used to fill fake data.
    private fun getCakesList(): List<CakeDto> {

        ('a'..'z').forEachIndexed { index, c ->
            cakes.add(CakeDto(
                title = c.toString(),
                image = "images",
                desc = "Image",

                ))
            // Added twice to check duplicate entries
            cakes.add(CakeDto(
                title = c.toString(),
                image = c.toString(),
                desc = c.toString(),

                ))
        }
        cakes.shuffle()

        return cakes
    }


}