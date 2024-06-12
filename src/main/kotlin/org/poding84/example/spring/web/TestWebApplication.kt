package org.poding84.example.spring.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["org.poding84.example.spring.web"])
class TestWebApplication

fun execute() {
    runApplication<TestWebApplication>()
}