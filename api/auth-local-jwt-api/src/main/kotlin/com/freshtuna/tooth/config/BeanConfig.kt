package com.freshtuna.tooth.config

import com.freshtuna.tooth.config.constant.JWTProperties
import com.freshtuna.tooth.jwt.JWTService
import com.freshtuna.tooth.jwt.incoming.JWTUseCase
import io.github.oshai.KotlinLogging
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeanConfig(
    private val jwtProperties: JWTProperties
) {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun jwtUseCase(): JWTUseCase {

        return JWTService(
            Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray()),
            Keys.hmacShaKeyFor(jwtProperties.refreshTokenSecret.toByteArray()),
            jwtProperties.accessTokenExpiredMileSeconds.toLong(),
            jwtProperties.refreshTokenExpiredMileSeconds.toLong(),
            jwtProperties.roleKey,
            jwtProperties.prefix
        )
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}