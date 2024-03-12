package org.poding84.example.generics.push

import org.poding84.example.generics.model.ProfilePack

fun interface PayloadConverter<T : Payload> {
    fun convert(incompletePayload: T, profilePack: ProfilePack): T
}