package com.codeal.apitestdemo

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
class TestsApplicationTests {

	@LocalServerPort
	private val port: Int = 0


	@BeforeEach
	private fun setupRestAssured() {
		RestAssured.baseURI = "http://localhost"
		RestAssured.port = port
	}

	@Test
	fun `POST saveData should return 200 if client A and client B return 200`() {
		stubFor(
				post(urlEqualTo("/saveDataA"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_OK)
						)
		)
		stubFor(
				post(urlEqualTo("/saveDataB"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_OK)
						)
		)

		given()
				.contentType(ContentType.JSON)
				.post("/saveData")
				.then()
				.statusCode(200)
	}

	@Test
	fun `POST saveData should return 500 if client A return 500`() {
		stubFor(
				post(urlEqualTo("/saveDataA"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						)
		)
		stubFor(
				post(urlEqualTo("/saveDataB"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_OK)
						)
		)

		given()
				.contentType(ContentType.JSON)
				.post("/saveData")
				.then()
				.statusCode(500)
	}

	@Test
	fun `POST saveData should return 500 if client B return 500`() {
		stubFor(
				post(urlEqualTo("/saveDataA"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_OK)
						)
		)
		stubFor(
				post(urlEqualTo("/saveDataB"))
						.willReturn(
								WireMock.aResponse()
										.withHeader("Content-Type", "application/json")
										.withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						)
		)

		given()
				.contentType(ContentType.JSON)
				.post("/saveData")
				.then()
				.statusCode(500)
	}

}
