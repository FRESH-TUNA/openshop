package com.freshtuna.openshop.config

import com.freshtuna.openshop.api.config.constant.JWTProperties
import com.freshtuna.openshop.jwt.JWTService
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import io.github.oshai.KotlinLogging
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeanConfig {
    private val logger = KotlinLogging.logger {}

    @Bean
    fun jwtProperties(): JWTProperties {
        return JWTProperties()
    }

    @Bean
    fun jwtUseCase(): JWTUseCase {
        logger.info(jwtProperties().secret)
        logger.info("ㅋㅋㅋㅋ")

        return JWTService(
            Keys.hmacShaKeyFor(jwtProperties().secret.toByteArray()),
            Keys.hmacShaKeyFor(jwtProperties().refreshTokenSecret.toByteArray()),
            jwtProperties().accessTokenExpiredMileSeconds.toLong(),
            jwtProperties().refreshTokenExpiredMileSeconds.toLong(),
            jwtProperties().roleKey,
            jwtProperties().prefix
        )
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}