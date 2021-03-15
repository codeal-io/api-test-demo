package com.codeal.apitestdemo.clients

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "clients")
@ConstructorBinding
data class ClientConfig(
    val clientAUrl: String,
    val clientBUrl: String
)