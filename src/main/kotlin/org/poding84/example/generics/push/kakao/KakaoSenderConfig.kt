package org.poding84.example.generics.push.kakao

import org.poding84.example.generics.push.PayloadConverter
import org.poding84.example.generics.push.PayloadSender
import org.poding84.example.generics.push.SenderConfig
import org.poding84.example.generics.model.NotificationSender
import org.poding84.example.generics.model.PropertyKey

data class KakaoSenderConfig(
    override val payloadSender: PayloadSender<KakaoPayload> = KakaoPayloadSender(),
    override val propertyKeys: List<PropertyKey> = listOf(),
    override val converter: PayloadConverter<KakaoPayload> = KakaoPayloadConverter(),
    override val chunkSize: Long = 1000
) : SenderConfig<KakaoPayload>() {
    override fun getNotificationSender(incompletePayload: KakaoPayload): NotificationSender {
        return getDefaultNotificationSender(incompletePayload)
    }
}