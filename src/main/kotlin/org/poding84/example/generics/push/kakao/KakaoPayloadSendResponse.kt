package org.poding84.example.generics.push.kakao

import org.poding84.example.generics.push.PayloadSendResponse

data class KakaoPayloadSendResponse(
    override val isSuccess: Boolean,
    val kakaoId: String
) : PayloadSendResponse