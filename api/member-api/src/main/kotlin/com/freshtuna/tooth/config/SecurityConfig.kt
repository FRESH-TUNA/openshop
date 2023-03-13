package com.freshtuna.tooth.config

import com.freshtuna.tooth.security.filter.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationFilter: AuthenticationFilter
) {

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
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    /** password 인코더 설정  */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /** CORS 설정  */
//    @Bean
//    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
//        val corsConfigSource = UrlBasedCorsConfigurationSource()
//        val corsConfig = CorsConfiguration()
//        corsConfig.allowedHeaders = Arrays.asList(corsProperties.getAllowedHeaders().split(","))
//        corsConfig.allowedMethods = Arrays.asList(corsProperties.getAllowedMethods().split(","))
//        corsConfig.allowedOrigins = Arrays.asList(corsProperties.getAllowedOrigins().split(","))
//        corsConfig.exposedHeaders = Arrays.asList(corsProperties.getExposedHeaders().split(","))
//        corsConfig.setMaxAge(corsProperties.getMaxAge())
//        corsConfig.allowCredentials = true
//        corsConfigSource.registerCorsConfiguration("/**", corsConfig)
//        return corsConfigSource
//    }
}
