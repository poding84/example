package org.poding84.example.ddb

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import org.poding84.example.ddb.model.DynamoDBConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.poding84.example.ddb.util.ReflectionUtil.toDynamoDBKey


data class JobQueryDto(
    val audience_id: String,
    val job_id: String,
)
data class Job(
    val audience_id: String,
    val job_id: String,
    val created_datetime: String,
    val expiration_datetime: String,
    val file_path: String,
    val message: String,
    val status: String,
    val updated_datetime: String,
)
fun ddbExample() {
    val client = DynamoDBClient(
        DynamoDBConfig(
            "ap-northeast-2",
            5,
            CoroutineScope(Dispatchers.IO)
        )
    )
    runBlocking {
        val v = client.queryItems<JobQueryDto, Job>("dfn-v2-backend-growth-popup-audience-load-job-dev",
            JobQueryDto("test_audience_id_1", "test_job_id_1")
        ).collect {
            println("hi")
            println(it)
        }
    }
}