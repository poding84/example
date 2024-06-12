package org.poding84.example.spring.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["org.poding84.example.spring.webflux"])
class TestWebFluxApplication

fun execute() {
    runApplication<TestWebFluxApplication>()
}