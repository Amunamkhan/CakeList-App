package com.droident.cakelistapp.data.repository

import com.droident.cakelistapp.data.remote.CakeApi
import com.droident.cakelistapp.data.remote.dto.CakeDto
import com.droident.cakelistapp.domain.repository.CakeRepository
import javax.inject.Inject

class CakeRepositoryImpl @Inject constructor(
    private val api: CakeApi
):CakeRepository {
    override suspend fun getCakes(): List<CakeDto> {
        return api.getCakes()
    }

}