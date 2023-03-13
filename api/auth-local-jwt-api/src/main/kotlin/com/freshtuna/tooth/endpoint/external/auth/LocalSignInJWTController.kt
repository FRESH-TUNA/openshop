package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.DataResponse

import com.freshtuna.tooth.util.HeaderUtil.Companion.addHeader
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.endpoint.external.auth.request.SignInRequest
import com.freshtuna.tooth.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.tooth.auth.incoming.LocalSignInUseCase
import com.freshtuna.tooth.auth.result.SignInJWTResult
import com.freshtuna.tooth.endpoint.external.auth.response.SignInJWTResponse

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalSignInJWTController(
    private val LocalSignInUseCase: LocalSignInUseCase
) : LocalMemberSignInSpec {

    /** 일반 로그인  */
    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: SignInRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = LocalSignInUseCase.signIn(request.toCommand()) as SignInJWTResult

        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return DataResponse.of(SignInJWTResponse(result.refreshToken.tokenString))
    }
}
