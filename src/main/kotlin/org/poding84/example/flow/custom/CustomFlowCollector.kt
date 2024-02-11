package org.poding84.example.flow.custom

fun interface CustomFlowCollector<T> {
    suspend fun emit(value: T)

    suspend fun delay(second: Int) {
        kotlinx.coroutines.delay(second.toLong())
    }
}