package org.poding84.example.generics.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.poding84.example.generics.model.ProfilePack
import org.poding84.example.generics.model.SendResult
import org.poding84.example.generics.model.SendingSpecification

class SenderExecutor {
    fun execute(
        sendingSpecification: SendingSpecification,
        profiles: Flow<ProfilePack>,
        onFinish: (SendResult) -> Unit
    ) {
        val results = sendingSpecification.sender.send(profiles)

        if (sendingSpecification.alternativeSenderSpecification == null) {
            return runBlocking {
                results.collect {
                    onFinish(it)
                }
            }
        }

        val failedProfiles = results.transform {
            if (it.failure != 0) {
                it.failedProfilePacks.forEach { profilePack ->
                    emit(profilePack)
                }
            } else {
                onFinish(it)
            }
        }

        execute(sendingSpecification.alternativeSenderSpecification, failedProfiles, onFinish)
    }
}