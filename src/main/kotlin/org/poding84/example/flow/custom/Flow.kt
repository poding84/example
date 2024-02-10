package org.poding84.example.flow.custom

fun <T> flow(block: suspend CustomFlowCollector<T>.() -> Unit ): CustomFlow<T> {
    return CustomFlow(block)
}
