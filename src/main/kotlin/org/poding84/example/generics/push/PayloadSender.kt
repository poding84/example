package org.poding84.example.generics.push

fun interface PayloadSender<T : Payload> {
    fun send(messages: List<T>): PayloadSendResponse
}