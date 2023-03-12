package com.freshtuna.openshop.security.filter

import com.freshtuna.openshop.api.security.filter.AuthenticationFilter
import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.api.security.userDetail.ToothUserDetail
import io.github.oshai.KotlinLogging
import jakarta.servlet.FilterChain

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class JWTAuthFilter(
    private val jwtUseCase: JWTUseCase
) : AuthenticationFilter() {

    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = HeaderUtil.getAuthorizationHeaderValue(response)

        if (!StringUtils.hasText(token)) {
            log.debug("토큰 정보 없음")
            return
        }

        val jwt = JWT.accessOf(token!!)

        try {
            jwtUseCase.checkAccessToken(jwt)
        } catch (e: RuntimeException) {
            log.debug("유효하지 않은 토큰")
            return
        }

        val authorities: List<SimpleGrantedAuthority> = jwtUseCase.claim(jwt, JWT.ROLE_KEY)
            .split(",")
            .map { s -> SimpleGrantedAuthority(s) }

        val userDetail = ToothUserDetail(
            jwtUseCase.idOfToken(jwt),
            authorities
        )

        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            userDetail, "", authorities
        )

        SecurityContextHolder.getContext().authentication = authentication
        log.debug("Security Context => 인증 정보 저장 완료: {}", authentication.name)

        filterChain.doFilter(request, response)
    }
}
