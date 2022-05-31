package br.com.sdr.forum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/forum")
class HelloController {
    @GetMapping
    fun hello(): String {
        return "Hello Amigo!!!"
    }

}