package org.poding84.example.generics.model

import org.poding84.example.generics.model.enums.PropertyType

typealias Properties = Map<PropertyKey, PropertyValue>

data class PropertyKey(
    val key: String,
    val type: PropertyType,
)