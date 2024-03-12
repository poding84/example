package org.poding84.example.generics.push.kakao

import org.poding84.example.generics.push.PayloadConverter
import org.poding84.example.generics.model.ProfilePack

class KakaoPayloadConverter : PayloadConverter<KakaoPayload> {
    override fun convert(incompletePayload: KakaoPayload, profilePack: ProfilePack): KakaoPayload {
        return KakaoPayload(
            phoneNumber = profilePack.unifiedId,
            message = incompletePayload.message,
            isConverted = true
        )
    }
}