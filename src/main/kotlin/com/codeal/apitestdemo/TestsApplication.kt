package com.codeal.apitestdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
class TestsApplication

fun main(args: Array<String>) {
	runApplication<TestsApplication>(*args)
}

@RestController
@RequestMapping("/")
class DemoUserController(
		private val clientA: ClientA,
		private val clientB: ClientB,
) {

	@PostMapping("/saveData")
	fun saveData() {
		clientA.saveData()
		clientB.saveData()
	}
}

@FeignClient(name = "clientA", url = "\${clientA.baseUrl}")
interface ClientA {

	@PostMapping("/saveDataA")
	fun saveData()

}

@FeignClient(name = "clientB", url = "\${clientB.baseUrl}")
interface ClientB {

	@PostMapping("/saveDataB")
	fun saveData()

}