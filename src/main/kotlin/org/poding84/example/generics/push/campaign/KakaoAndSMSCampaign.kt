package org.poding84.example.generics.push.campaign

import org.poding84.example.generics.model.Properties
import org.poding84.example.generics.model.PropertyKey
import org.poding84.example.generics.model.PropertyValue
import org.poding84.example.generics.model.Sendable
import org.poding84.example.generics.model.enums.PropertyType
import org.poding84.example.generics.push.Payload
import org.poding84.example.generics.push.kakao.KakaoPayload
import org.poding84.example.generics.push.sms.SMSPayload

data class KakaoAndSMSCampaign(
    val id: String
) : Sendable {
    override fun getPayloadsAndProperties(): List<Pair<Payload, Properties>> {
        return listOf(
            KakaoPayload(
                "1234567890",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),
            SMSPayload(
                "1234567890",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),
        )
    }
}
