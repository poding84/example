package org.poding84.example.generics.service

import org.poding84.example.generics.push.Payload
import org.poding84.example.generics.push.SenderConfig
import org.poding84.example.generics.push.kakao.KakaoPayload
import org.poding84.example.generics.push.kakao.KakaoSenderConfig
import org.poding84.example.generics.model.Properties
import org.poding84.example.generics.model.SendingSpecification
import org.poding84.example.generics.push.sms.SMSPayload
import org.poding84.example.generics.push.sms.SMSSenderConfig

class SendingSpecificationProvider(
    private val smsSenderConfig: SenderConfig<SMSPayload> = SMSSenderConfig(),
    private val kakaoSenderConfig: SenderConfig<KakaoPayload> = KakaoSenderConfig()

) {
    fun getSendingSpec(payload: Payload, properties: Properties): SendingSpecification {
        return when (payload) {
            is SMSPayload -> {
                smsSenderConfig.getSendingSpec(payload, properties)
            }

            is KakaoPayload -> {
                kakaoSenderConfig.getSendingSpec(payload, properties)
            }

            else -> throw IllegalArgumentException("Unsupported payload: $payload")
        }
    }
}