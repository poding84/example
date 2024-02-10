package org.poding84.example.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.poding84.example.flow.custom.flow


fun flowExample() {
    runBlocking {
        val f = flow {
            emit(10)
            delay(1000)
            emit(20)
            delay(1000)
            emit(30)
        }

        f.collect {
            println("emit $it!")
        }

    }
}