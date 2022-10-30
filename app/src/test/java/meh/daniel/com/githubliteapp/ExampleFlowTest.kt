package meh.daniel.com.githubliteapp

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.junit.Test

class ExampleFlowTest {

    @Test
    suspend fun whatIsFlow(){
        val flow = flowOf(1, 2, 3, 4, 5)
        println("Чёткие цифры помноженные на 10:")
        flow
            .filter { it % 2 == 0 }
            .map { it * 10 }
            .collect {
                println(it)
            }
        println("Не пацанские цифры:")
        flow
            .filter { it % 2 == 1 }
            .collect {
                println(it)
            }
    }

}