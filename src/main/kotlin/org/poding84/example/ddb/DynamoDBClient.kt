package org.poding84.example.ddb

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import org.poding84.example.ddb.helper.DynamoDBHelperImpl
import org.poding84.example.ddb.model.DynamoDBConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.poding84.example.ddb.util.ReflectionUtil.toClass
import org.poding84.example.ddb.util.ReflectionUtil.toDynamoDBKey
import org.poding84.example.ddb.util.ReflectionUtil.transformToClass

class DynamoDBClient(
    dynamoDBConfig: DynamoDBConfig
) {
    val ddbHelper = DynamoDBHelperImpl(dynamoDBConfig.region)
    private val concurrency = dynamoDBConfig.concurrency
    private val chunkSize = 20

    suspend inline fun<S: Any, reified T> queryItems(tableName: String, key: S): Flow<T> {
        return ddbHelper.queryItems(tableName, key.toDynamoDBKey()).transformToClass()
    }

    suspend inline fun<S: Any, reified T> getItem(tableName: String, key: S): T {
        return ddbHelper.getItem(tableName, key.toDynamoDBKey()).item!!.toClass()
    }

    suspend fun putItems(tableName: String, items: Flow<Map<String, AttributeValue>>) {
        runBlocking(Dispatchers.IO) {
            val t = mutableListOf<Map<String,AttributeValue>>()
            items.collect {
                t.add(it)
            }
            ddbHelper.putItems(tableName, t)
        }
    }

}