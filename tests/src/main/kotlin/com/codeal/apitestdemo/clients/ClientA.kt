package com.codeal.apitestdemo.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "clientA", url = "\${clientA.baseUrl}")
interface ClientA {

    @RequestMapping(
            method = [RequestMethod.POST],
            value = ["/saveDataA"],
            produces = ["application/json"]
    )
    fun saveData()

}