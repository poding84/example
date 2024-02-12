package org.poding84.example.ddb.model

import kotlinx.coroutines.CoroutineScope

data class DynamoDBConfig(
    val region: String,
    val concurrency: Int,
    val coroutineScope: CoroutineScope
)