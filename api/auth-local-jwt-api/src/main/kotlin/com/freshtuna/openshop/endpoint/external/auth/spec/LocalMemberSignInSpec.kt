package com.freshtuna.openshop.endpoint.external.auth.spec

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.endpoint.external.auth.request.SignInRequest
import jakarta.servlet.http.HttpServletResponse

interface LocalMemberSignInSpec {
    fun signIn(request: SignInRequest, response: HttpServletResponse): BasicResponse
}
