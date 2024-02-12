package org.poding84.example.reflection

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.*
import kotlin.reflect.typeOf
import kotlin.system.measureTimeMillis


data class Entity(
    val name: String = "Seokwoo",
    val age: Long,
    val description: String = "hi",
    val attribute: List<String> = listOf("bye", "good"),
)

fun <T : Any>createEntity(args: Array<Any>, clazz: KClass<T>): T {
    return clazz.primaryConstructor!!.call(*args)
}
fun reflectionExample() {
    val params = Entity::class.primaryConstructor!!.parameters
    val v = measureTimeMillis {
        val l = (1..1_000_000)
            .map{
                mapOf(
                    "name" to "hi",
                    "age" to it.toLong(),
                    "description" to "hi",
                    "attribute" to listOf("hihi")
                )
            }
            .map {
                params.map {param ->
                    it[param.name]!!
                }
            }
            .map {
            createEntity(it.toTypedArray(), Entity::class)
//            Entity (
//                it[0] as String,
//                it[1] as Long,
//                it[2] as String,
//                it[3] as List<String>
//            )
        }
        val s = l.foldRight(0L) { t, acc ->
            t.age + acc
        }
        println(s)
    }
    println(v)
}