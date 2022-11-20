package com.droident.cakelistapp.domain.repository

import com.droident.cakelistapp.data.remote.dto.CakeDto

interface CakeRepository {
    suspend fun getCakes(): List<CakeDto>
}