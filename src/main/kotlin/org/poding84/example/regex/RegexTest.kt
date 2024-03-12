package org.poding84.example.regex

class RegexPersonalizationTemplateEngine {
    private val testRegex = "\\\$\\{(.*)}".toRegex()
    fun render(input: String, map: Map<String, String>): String {
        return replaceString(input, map)
    }

    private fun replaceString(originalString: String, replacementMap: Map<String, String>): String {
        var resultString = originalString

        for ((key, value) in replacementMap) {
            val placeholderRegex = "\\\$\\{( *)${key}( *)}".toRegex()
            resultString = resultString.replace(placeholderRegex, value)
        }

        if (resultString.contains(testRegex)) {
            throw IllegalArgumentException("Unresolved placeholders in the template: $resultString")
        }

        return resultString
    }
}

fun regexTest() {
    val engine = RegexPersonalizationTemplateEngine()
    val input =
        "Hello, "
    val map = mapOf("name" to "John", "age" to "24")
    val result = (1..100_000_000).map {
        if (it % 100_000 == 0) println(it)
        //engine.render(input, map)
        input + map["name"] + map["age"]
    }
    println(result.size)
    // Hello, John!
}