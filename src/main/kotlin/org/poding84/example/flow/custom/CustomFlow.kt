package org.poding84.example.flow.custom

class CustomFlow<T>(
    private val block: suspend CustomFlowCollector<T>.() -> Unit
) {
    suspend fun collect(collector: CustomFlowCollector<T>) {
        block.invoke(collector)
    }
}