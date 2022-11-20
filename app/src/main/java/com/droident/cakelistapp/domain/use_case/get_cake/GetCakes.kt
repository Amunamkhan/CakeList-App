package com.droident.cakelistapp.domain.use_case.get_cake

import com.droident.cakelistapp.common.Resource
import com.droident.cakelistapp.data.remote.dto.toCake
import com.droident.cakelistapp.domain.model.Cake
import com.droident.cakelistapp.domain.repository.CakeRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException


import javax.inject.Inject

class GetCakes @Inject constructor(
    private val repository: CakeRepository,
) {
    operator fun invoke(): Flow<Resource<List<Cake>>> = flow {

        try {
            emit(Resource.Loading())
            val cakes = repository.getCakes().map { it.toCake() }.filter {
                it.image != ""
            }.sortedBy { it.title }.distinctBy { it.title }
            emit(Resource.Success(cakes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An excepted error occurred"))
        } catch (e: IOException) {

            emit(Resource.Error("Could not reach server, Check your internet connection"))
        }
    }
}