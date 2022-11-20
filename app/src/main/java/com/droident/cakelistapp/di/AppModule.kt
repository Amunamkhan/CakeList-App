package com.droident.cakelistapp.di

import com.droident.cakelistapp.common.Constants
import com.droident.cakelistapp.data.remote.CakeApi
import com.droident.cakelistapp.data.repository.CakeRepositoryImpl
import com.droident.cakelistapp.domain.repository.CakeRepository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCakeApi(): CakeApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(CakeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CakeApi): CakeRepository {
        return CakeRepositoryImpl(api)
    }


}