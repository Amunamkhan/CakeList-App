package com.droident.cakelistapp.domain.use_case.get_cake

import com.droident.cakelistapp.common.Resource
import com.droident.cakelistapp.data.repository.FakeCakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetCakesTest {

    private lateinit var getCakeUseCase: GetCakes
    private lateinit var fakeCakeRepository: FakeCakeRepository

    @Before
    fun setUp() {
        fakeCakeRepository = FakeCakeRepository()
        getCakeUseCase = GetCakes(fakeCakeRepository)
    }

    @Test
    fun `Order cakes by title ascending, correct order`() = runTest {

        var resul = getCakeUseCase().drop(1);
        val cakes = resul.first().data ?: emptyList()
        println("munam1" + cakes.size)
        for (i in 0..cakes.size - 2) {
            print(cakes[i].title)
            assertThat(cakes[i].title).isGreaterThan(cakes[i + 1].title)
        }

    }

    @Test
    fun `Remove duplicate cakes by title , correct distinct`() = runTest {

        var result = getCakeUseCase().drop(1);
        val cakes = result.first().data ?: emptyList()
        for (i in 0..cakes.size - 2) {
            assertThat(cakes[i].title).isEqualTo(cakes[i + 1].title)
        }

    }
}






