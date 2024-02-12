package org.poding84.example.ddb.helper

import aws.sdk.kotlin.services.dynamodb.model.*
import kotlinx.coroutines.flow.Flow

interface DynamoDBHelper {
    suspend fun getItem(tableName: String, key: Map<String, AttributeValue>): GetItemResponse
    suspend fun putItem(tableName: String, item: Map<String, AttributeValue>): PutItemResponse
    suspend fun putItems(tableName: String, item: List<Map<String, AttributeValue>>): BatchWriteItemResponse
    suspend fun deleteItem(tableName: String, key: Map<String, AttributeValue>): DeleteItemResponse
    suspend fun queryItems(tableName: String, key: Map<String, AttributeValue>): Flow<Map<String, AttributeValue>>
}