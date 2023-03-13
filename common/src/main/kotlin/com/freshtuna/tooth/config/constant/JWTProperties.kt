package com.freshtuna.tooth.config.constant

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tooth.jwt")
data class JWTProperties(
    var secret: String = "",
    var refreshTokenSecret: String = "",
    var accessTokenExpiredMileSeconds: String = "",
    var refreshTokenExpiredMileSeconds: String = "",
    var roleKey: String = "",
    var prefix: String = ""
)
