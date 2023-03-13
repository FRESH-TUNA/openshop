package com.freshtuna.tooth.config.constant

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tooth.local")
class LocalProperties(
    var idPolicy: String = ""
)
