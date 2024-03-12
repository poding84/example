package org.poding84.example.generics.push.sms

import org.poding84.example.generics.push.Payload

data class SMSPayload(
    val phoneNumber: String,
    val message: String,
    override val isConverted: Boolean
) : Payload