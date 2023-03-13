package com.freshtuna.tooth.config.constant

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tooth.cors")
class CORSProperties(
    var allowedOrigins: String = "",
    var allowedMethods: String = "",
    var allowedHeaders: String = "",
    var exposedHeaders: String = "",
    var maxAge: Long = 0L
)
