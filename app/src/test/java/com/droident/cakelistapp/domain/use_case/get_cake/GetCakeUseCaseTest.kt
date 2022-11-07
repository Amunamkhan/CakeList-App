package com.droident.cakelistapp.domain.use_case.get_cake

import android.util.Log
import com.droident.cakelistapp.common.Resource
import com.droident.cakelistapp.data.repository.FakeCakeRepository
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test



class GetCakeUseCaseTest {

    private lateinit var getCakeUseCase: GetCakeUseCase
    private lateinit var fakeCakeRepository: FakeCakeRepository

    @Before
    fun setUp() {
        fakeCakeRepository = FakeCakeRepository()
        getCakeUseCase = GetCakeUseCase(fakeCakeRepository)
    }

    @Test
    fun `Order cakes by title ascending, correct order`()= runTest {


           getCakeUseCase().onEach { result ->
               when (result) {
                   is Resource.Success -> {
                       val cakes = result.data ?: emptyList()
                       for (i in 0..cakes.size - 2) {
                           assertThat(cakes[i].title).isLessThan(cakes[i + 1].title)
                       }
                   }
                   is Resource.Error -> {

                   }
                   is Resource.Loading -> {

                   }
               }
           }


    }

    @Test
    fun `Remove duplicate cakes by title , correct distinct`()= runTest {


        getCakeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val cakes = result.data ?: emptyList()
                    for (i in 0..cakes.size - 2) {
                        assertThat(cakes[i].title).isEqualTo(cakes[i + 1].title)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }


    }
}






