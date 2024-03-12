package org.poding84.example.generics.push.sms

import org.poding84.example.generics.push.PayloadSender
import org.poding84.example.generics.push.PayloadSendResponse

class SMSPayloadSender : PayloadSender<SMSPayload> {
    override fun send(messages: List<SMSPayload>): PayloadSendResponse {
        return SMSPayloadSendResponse(
            true,
            "1"
        )
    }
}