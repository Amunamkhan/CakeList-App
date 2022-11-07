package com.droident.cakelistapp.data.repository

import com.droident.cakelistapp.data.remote.dto.CakeDto
import com.droident.cakelistapp.domain.repository.CakeRepository


class FakeCakeRepository : CakeRepository {
    override suspend fun getCakes(): List<CakeDto> {
        val cakes = mutableListOf<CakeDto>()
        ('a'..'z').forEachIndexed { index, c ->
            cakes.add(
                CakeDto(
                    title = c.toString(),
                    image = c.toString(),
                    desc = c.toString(),

                )
            )

            cakes.add(
                CakeDto(
                    title = c.toString(),
                    image = c.toString(),
                    desc = c.toString(),

                    )
            )
        }
        cakes.shuffle()
        return cakes
    }


}