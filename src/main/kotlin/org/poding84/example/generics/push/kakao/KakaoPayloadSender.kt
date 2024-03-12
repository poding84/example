package org.poding84.example.generics.push.kakao

import org.poding84.example.generics.push.PayloadSender
import org.poding84.example.generics.push.PayloadSendResponse

class KakaoPayloadSender : PayloadSender<KakaoPayload> {
    override fun send(messages: List<KakaoPayload>): PayloadSendResponse {
        if (messages.any { it.phoneNumber == "1" }) {
            return KakaoPayloadSendResponse(
                true,
                "13023872"
            )
        }
        return KakaoPayloadSendResponse(
            false,
            "13023872"
        )
    }
}