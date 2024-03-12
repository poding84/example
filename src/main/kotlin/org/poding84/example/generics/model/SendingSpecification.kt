package org.poding84.example.generics.model

data class SendingSpecification(
    val properties: Properties,
    val sender: NotificationSender,
    val alternativeSenderSpecification: SendingSpecification?
)