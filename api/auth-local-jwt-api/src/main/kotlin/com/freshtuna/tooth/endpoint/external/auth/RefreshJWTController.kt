package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.util.HeaderUtil
import com.freshtuna.tooth.config.constant.Url

import com.freshtuna.tooth.endpoint.external.auth.request.RefreshJWTRequest
import com.freshtuna.tooth.endpoint.external.auth.spec.RefreshJWTSpec
import com.freshtuna.tooth.jwt.JWT
import com.freshtuna.tooth.jwt.incoming.JWTUseCase
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshJWTController(
    private val jwtUseCase: JWTUseCase
) : RefreshJWTSpec {

    @PostMapping(Url.EXTERNAL.JWT_REFRESH)
    override fun refresh(@RequestBody request: RefreshJWTRequest, response: HttpServletResponse): BasicResponse {
        val newAccessToken = jwtUseCase.refresh(JWT.refreshOf(request.refresh))

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, newAccessToken.tokenString, response)

        return MessageResponse.OK
    }
}
