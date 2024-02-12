package org.poding84.example.ddb.helper

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DynamoDBHelperImpl(
    region: String,
) : DynamoDBHelper {
    private val ddbClient = DynamoDbClient {
        this.region = region
    }

    override suspend fun getItem(tableName: String, key: Map<String, AttributeValue>): GetItemResponse {
        val request = GetItemRequest {
            this.tableName = tableName
            this.key = key
        }
        return ddbClient.getItem(request)
    }

    override suspend fun putItem(tableName: String, item: Map<String, AttributeValue>): PutItemResponse {
        val request = PutItemRequest {
            this.tableName = tableName
            this.item = item
        }
        return ddbClient.putItem(request)
    }

    override suspend fun putItems(tableName: String, item: List<Map<String, AttributeValue>>): BatchWriteItemResponse {
        val request = BatchWriteItemRequest {
            this.requestItems = mapOf(tableName to item.map {
                WriteRequest {
                    putRequest = PutRequest {
                        this.item = it
                    }
                }
            })
        }
        return ddbClient.batchWriteItem(request)
    }


    override suspend fun deleteItem(tableName: String, key: Map<String, AttributeValue>): DeleteItemResponse {
        val request = DeleteItemRequest {
            this.tableName = tableName
            this.key = key
        }
        return ddbClient.deleteItem(request)
    }

    override suspend fun queryItems(
        tableName: String,
        key: Map<String, AttributeValue>
    ): Flow<Map<String, AttributeValue>> = flow {
        val request = QueryRequest {
            this.tableName = tableName
            this.keyConditionExpression = key.map { "${it.key} = :${it.key}" }.joinToString(" AND ")
            this.expressionAttributeValues = key.mapKeys { ":${it.key}" }
        }
        val response = ddbClient.query(request)
        response.items!!.forEach { emit(it) }
    }
}