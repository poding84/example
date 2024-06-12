package org.poding84.example.spring.web

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class WebController {
    @GetMapping
    suspend fun healthCheck(): Boolean {
        delay(1000)
        return true
    }
}