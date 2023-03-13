package com.freshtuna.tooth.config

import io.github.oshai.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    private val logger = KotlinLogging.logger {}

    /** CORS 관련 설정 정보  */
//    private val corsProperties: CorsProperties? = null

    /** Spring Security Filter Chain 관련 설정  */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors()
            .and() /* session 사용하지 않음 (STATELESS) */
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .csrf().disable()
            .exceptionHandling() /* 유효한 자격증명을 제공하지 않는 경우 */
            .and() /* URI 기반 인증/인가 설정 */
            .authorizeHttpRequests()
            .anyRequest().permitAll()
            .and()
            .build()
    }

    /** password 인코더 설정  */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
