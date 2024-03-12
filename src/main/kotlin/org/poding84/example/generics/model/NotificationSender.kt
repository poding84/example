package org.poding84.example.generics.model

import kotlinx.coroutines.flow.Flow

fun interface NotificationSender {
    fun send(profiles: Flow<ProfilePack>): Flow<SendResult>
}