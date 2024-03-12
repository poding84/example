package org.poding84.example.generics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.poding84.example.generics.model.Sendable
import org.poding84.example.generics.push.campaign.KakaoAndSMSCampaign
import org.poding84.example.generics.push.kakao.KakaoPayload
import org.poding84.example.generics.push.kakao.KakaoPayloadSendResponse
import org.poding84.example.generics.model.ProfilePack
import org.poding84.example.generics.model.PropertyKey
import org.poding84.example.generics.model.PropertyValue
import org.poding84.example.generics.model.SendResult
import org.poding84.example.generics.model.enums.PropertyType
import org.poding84.example.generics.push.sms.SMSPayload
import org.poding84.example.generics.push.sms.SMSPayloadSendResponse
import org.poding84.example.generics.service.SenderExecutor
import org.poding84.example.generics.service.SendingSpecificationBuilder
import org.poding84.example.generics.service.SendingSpecificationProvider


val sampleProfiles = flowOf(
    ProfilePack("1", mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("John"))),
    ProfilePack("2", mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("Jane"))),
)

fun senderTest() {
    sendWithoutCampaign()
}

fun sendWithCampaign() {
    // Prepare campaign
    val campaign = KakaoAndSMSCampaign("Hello, World!")

    // And send!
    send(campaign, sampleProfiles) {
        when (it.response) {
            is SMSPayloadSendResponse -> {
                println("SMS Success: ${it.response}")
                // Send to Consistency checker
            }

            is KakaoPayloadSendResponse -> {
                println("Kakao Success: ${it.response}")
            }
        }
    }
}

// This can be used when pushing test messages without a campaign
fun sendWithoutCampaign() {
    val sendable = Sendable {
        listOf(
            KakaoPayload(
                "123",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),

            KakaoPayload(
                "123",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),


            KakaoPayload(
                "123",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),


            SMSPayload(
                "abc",
                "Hello, World!",
                false
            ) to mapOf(PropertyKey("name", PropertyType.User) to PropertyValue("hi")),
        )
    }

    send(sendable, sampleProfiles) {
        when (it.response) {
            is SMSPayloadSendResponse -> {
                println("SMS : ${it.response}")
                // Send to Consistency checker
            }

            is KakaoPayloadSendResponse -> {
                println("Kakao : ${it.response}")
            }
        }
    }
}

fun send(sendable: Sendable, profiles: Flow<ProfilePack>, onFinish: (SendResult) -> Unit) {

    val sendingSpecification =
        SendingSpecificationBuilder(SendingSpecificationProvider())
            .addSendable(sendable)
            .build()

    SenderExecutor().execute(sendingSpecification, profiles, onFinish)
}