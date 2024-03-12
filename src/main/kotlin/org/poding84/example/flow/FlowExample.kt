package org.poding84.example.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


suspend fun wow(i: Int): Int {
    if (i == 1) {
        delay(3000)
    }
    delay(1000)
    return i + 100
}

fun getFlow() = flow {
    for (i in 1..100) {
        delay(1)
        println("emit $i")
        emit(i)
    }
}

var runCount = 0
suspend fun usefulFunction(i: Int): Int {
    runCount++
    if (i == 1) {
        delay(10_000)
    }
    delay(1000)
    println("useful $i")
    if (runCount > 10) {
        throw Exception("Function is not available!!")
    }
    runCount--
    return i
}

@OptIn(ExperimentalCoroutinesApi::class)
fun flowExample() {
    val f = flow {
        for (i in 1..100) {
            emit(i)
        }
    }

    val newF = f.flatMapMerge(10) {
        flow {
            emit(usefulFunction(it))
        }
    }
    runBlocking {
        val t = measureTimeMillis {
            val l = newF.collect {
                println(it)
            }
        }

        println(t)
    }
}