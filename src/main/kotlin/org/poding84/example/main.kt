package org.poding84.example

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import org.poding84.example.flow.flowExample

fun main() {
    flowExample()
    val f = (1..10).asFlow()
    f.take(10)
}