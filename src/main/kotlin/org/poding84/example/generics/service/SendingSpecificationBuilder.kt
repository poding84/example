package org.poding84.example.generics.service

import org.poding84.example.generics.model.Properties
import org.poding84.example.generics.model.Sendable
import org.poding84.example.generics.model.SendingSpecification
import org.poding84.example.generics.push.Payload

class SendingSpecificationBuilder(
    private val sendingSpecificationProvider: SendingSpecificationProvider
) {
    private val sendingSpecs = mutableListOf<SendingSpecification>()

    fun addSendable(sendable: Sendable): SendingSpecificationBuilder {
        // Add the payloads to the sending specification
        val payloads = sendable.getPayloadsAndProperties()
        val primary = payloads.first()
        val secondary = payloads.drop(1)

        this.addPrimaryPayload(primary.first, primary.second)
        secondary.forEach { (payload, properties) ->
            this.addSecondaryPayload(payload, properties)
        }

        return this
    }

    private fun addPrimaryPayload(payload: Payload, properties: Properties): SendingSpecificationBuilder {
        require(sendingSpecs.isEmpty()) { "Primary payload must be added first" }
        val spec = sendingSpecificationProvider.getSendingSpec(payload, properties)
        sendingSpecs.add(spec)
        return this
    }

    private fun addSecondaryPayload(payload: Payload, properties: Properties): SendingSpecificationBuilder {
        require(sendingSpecs.isNotEmpty()) { "Primary payload must be added first" }
        val spec = sendingSpecificationProvider.getSendingSpec(payload, properties)
        sendingSpecs.add(spec)
        return this
    }

    fun build(): SendingSpecification {
        require(sendingSpecs.isNotEmpty()) { "At least one payload must be added" }
        val sendingSpec = sendingSpecs.foldRight(null as SendingSpecification?) { spec, acc ->
            SendingSpecification(
                spec.properties,
                spec.sender,
                acc
            )
        }

        return sendingSpec!!
    }
}