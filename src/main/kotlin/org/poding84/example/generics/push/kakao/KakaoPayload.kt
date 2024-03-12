package org.poding84.example.generics.push.kakao

import org.poding84.example.generics.push.Payload

data class KakaoPayload(
    val phoneNumber: String,
    val message: String,
    override val isConverted: Boolean
) : Payload