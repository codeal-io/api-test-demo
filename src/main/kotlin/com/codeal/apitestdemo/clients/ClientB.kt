package com.codeal.apitestdemo.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "clientB", url = "\${clientB.baseUrl}")
interface ClientB {

    @RequestMapping(
            method = [RequestMethod.POST],
            value = ["/saveDataB"],
            produces = ["application/json"]
    )
    fun saveData()

}