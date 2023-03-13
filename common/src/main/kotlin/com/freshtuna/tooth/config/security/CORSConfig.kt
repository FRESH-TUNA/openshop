package com.freshtuna.tooth.config.security

import com.freshtuna.tooth.config.constant.CORSProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CORSConfig(private val corsProperties: CORSProperties) {

    /** CORS 설정  */
    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val corsConfigSource = UrlBasedCorsConfigurationSource()
        val corsConfig = CorsConfiguration()

        corsConfig.allowedHeaders = corsProperties.allowedHeaders.split(",")
        corsConfig.allowedMethods = corsProperties.allowedMethods.split(",")
        corsConfig.allowedOrigins = corsProperties.allowedOrigins.split(",")
        corsConfig.exposedHeaders = corsProperties.exposedHeaders.split(",")
        corsConfig.maxAge = corsProperties.maxAge
        corsConfig.allowCredentials = true
        corsConfigSource.registerCorsConfiguration("/**", corsConfig)

        return corsConfigSource
    }
}
