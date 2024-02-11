package org.poding84.example.flow.custom

class CustomFlow<T>(
    private val block: suspend CustomFlowCollector<T>.() -> Unit
) {
    private var until = -1
    suspend fun collect(collector: CustomFlowCollector<T>) {
        try {
            block.invoke(object: CustomFlowCollector<T> {
                private var curr = 0
                override suspend fun emit(value: T) {
                    collector.emit(value)
                    curr += 1
                    if (until <= curr) {
                        throw StopFlowException()
                    }
                }
            })
        }
        catch (e: StopFlowException) {
            println("Aborted")
        }
    }

    fun take(until: Int): CustomFlow<T> {
        this.until = until
        return this
    }
}