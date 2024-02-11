package org.poding84.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.poding84.example.flow.custom.CustomFlow

fun flowExample() {
    runBlocking {
        val f = CustomFlow {
            (1..10).forEach {
                emit(it)
                delay(1000)
            }
        }

        f.take(3).collect {
            delay(2000)
            println("collect $it!")
        }
    }
}