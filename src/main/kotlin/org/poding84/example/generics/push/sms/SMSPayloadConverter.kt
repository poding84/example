package org.poding84.example.generics.push.sms

import org.poding84.example.generics.push.PayloadConverter
import org.poding84.example.generics.model.ProfilePack

class SMSPayloadConverter : PayloadConverter<SMSPayload> {
    override fun convert(incompletePayload: SMSPayload, profilePack: ProfilePack): SMSPayload {
        return SMSPayload(
            phoneNumber = profilePack.unifiedId,
            message = incompletePayload.message,
            isConverted = true
        )
    }
}