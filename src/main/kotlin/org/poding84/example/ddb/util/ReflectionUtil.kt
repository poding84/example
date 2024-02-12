package org.poding84.example.ddb.util

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import org.poding84.example.ddb.model.DynamoDBItemTypeInvalidException
import org.poding84.example.ddb.util.ReflectionUtil.toClass
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.typeOf


typealias DynamoDBItem = Map<String, AttributeValue>
object ReflectionUtil {
    fun <T : Any> T.toDynamoDBKey(): DynamoDBItem {
        return this::class.memberProperties.associate {
            it.name to getAttributeValue(it.getter.call(this)!!)
        }
    }

    inline fun <reified T: Any> DynamoDBItem.toClass(): T {
        val args = T::class.primaryConstructor!!.parameters.map { param ->
            getValueGetter(param.type)(this[param.name]!!)
        }
        return createEntity(args.toTypedArray(), T::class)
    }

    inline fun <reified T: Any> Flow<DynamoDBItem>.transformToClass(): Flow<T> {
        val args = T::class.primaryConstructor!!.parameters.map { param ->
            param.name!! to getValueGetter(param.type)
        }
        return this.transform {item ->
            val c = (createEntity(args
                .map {
                    val value = item[it.first] ?: throw DynamoDBItemTypeInvalidException(it.first)
                    it.second(value)
                }
                .toTypedArray(), T::class
            ))
            emit(c)
        }
    }



    fun <T : Any>createEntity(args: Array<Any>, clazz: KClass<T>): T {
        return clazz.primaryConstructor!!.call(*args)
    }

    private fun getAttributeValue(value: Any): AttributeValue {
        return when (value::class.starProjectedType) {
            typeOf<String>() -> AttributeValue.S(value as String)
            typeOf<Long>() -> AttributeValue.N(value.toString())
            typeOf<ByteArray>() -> AttributeValue.B(value as ByteArray)
            typeOf<Boolean>() -> AttributeValue.Bool(value as Boolean)
            typeOf<List<String>>() -> AttributeValue.Ss(value as List<String>)
            typeOf<List<Long>>() -> AttributeValue.Ns(value as List<String>)
            typeOf<List<ByteArray>>() -> AttributeValue.Bs(value as List<ByteArray>)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }


    fun getValueGetter(type: KType): (AttributeValue) -> Any {
        when (type) {
            typeOf<String>() -> return { it.asS() }
            typeOf<Long>() -> return { it.asL() }
            typeOf<ByteArray>() -> return { it.asB() }
            typeOf<Boolean>() -> return { it.asBool() }
            typeOf<List<String>>() -> return { it.asSs() }
            typeOf<List<Long>>() -> return { it.asNs() }
            typeOf<List<ByteArray>>() -> return { it.asBs() }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}