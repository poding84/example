package org.poding84.example.generics.model

import org.poding84.example.generics.push.Payload


fun interface Sendable {
    fun getPayloadsAndProperties(): List<Pair<Payload, Properties>>
}