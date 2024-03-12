package org.poding84.example.generics.push.sms

import org.poding84.example.generics.push.PayloadSendResponse

data class SMSPayloadSendResponse(
    override val isSuccess: Boolean,
    val messageId: String
) : PayloadSendResponse
