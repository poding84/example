package org.poding84.example.generics.push.sms

import org.poding84.example.generics.model.NotificationSender
import org.poding84.example.generics.model.PropertyKey
import org.poding84.example.generics.push.PayloadConverter
import org.poding84.example.generics.push.PayloadSender
import org.poding84.example.generics.push.SenderConfig

data class SMSSenderConfig(
    override val payloadSender: PayloadSender<SMSPayload> = SMSPayloadSender(),
    override val propertyKeys: List<PropertyKey> = listOf(),
    override val converter: PayloadConverter<SMSPayload> = SMSPayloadConverter(),
    override val chunkSize: Long = 1000
) : SenderConfig<SMSPayload>() {
    override fun getNotificationSender(incompletePayload: SMSPayload): NotificationSender {
        return getDefaultNotificationSender(incompletePayload)
    }

}