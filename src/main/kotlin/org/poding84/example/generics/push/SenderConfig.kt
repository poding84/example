package org.poding84.example.generics.push

import kotlinx.coroutines.flow.transform
import org.poding84.example.generics.model.*

abstract class SenderConfig<T : Payload> {
    abstract val payloadSender: PayloadSender<T>
    abstract val propertyKeys: List<PropertyKey>
    abstract val converter: PayloadConverter<T>
    abstract val chunkSize: Long
    fun getSendingSpec(incompletePayload: T, properties: Properties): SendingSpecification {
        val channelProperties = this.propertyKeys.associateWith {
            PropertyValue(null)
        }

        return SendingSpecification(
            properties + channelProperties,
            this.getNotificationSender(incompletePayload),
            null
        )
    }

    abstract fun getNotificationSender(incompletePayload: T): NotificationSender

    protected fun getDefaultNotificationSender(incompletePayload: T) = NotificationSender {
        it.transform { profilePack: ProfilePack ->
            // TODO: emit by chunkSize
            emit(listOf(profilePack))
        }.transform { profilePacks ->
            val response = payloadSender.send(
                profilePacks.map { profilePack ->
                    converter.convert(incompletePayload, profilePack)
                }
            )
            val result = if (response.isSuccess) {
                SendResult(
                    profilePacks.size,
                    0,
                    response,
                    listOf()
                )
            } else {
                SendResult(
                    0,
                    profilePacks.size,
                    response,
                    profilePacks
                )
            }
            emit(result)
        }
    }
}