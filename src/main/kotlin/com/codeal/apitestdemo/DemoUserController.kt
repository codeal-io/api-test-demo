package com.codeal.apitestdemo

import com.codeal.apitestdemo.clients.ClientA
import com.codeal.apitestdemo.clients.ClientB
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class DemoUserController(
        private val clientA: ClientA,
        private val clientB: ClientB,
) {

    @PostMapping("/saveData",
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun saveData() {
        clientA.saveData()
        clientB.saveData()
    }
}