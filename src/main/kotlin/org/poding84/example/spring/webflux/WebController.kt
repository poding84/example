package org.poding84.example.spring.webflux

import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class WebController {
    @GetMapping
    fun healthCheck(): Mono<Boolean> {
        return mono {
            delay(100)
            true
        }
    }
}