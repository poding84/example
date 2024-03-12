package org.poding84.example.generics.model

import org.poding84.example.generics.push.PayloadSendResponse

data class SendResult(
    val success: Int,
    val failure: Int,
    val response: PayloadSendResponse,
    val failedProfilePacks: List<ProfilePack>
)