package com.freshtuna.tooth.endpoint.external.auth.spec

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.endpoint.external.auth.request.SignInRequest
import jakarta.servlet.http.HttpServletResponse

interface LocalMemberSignInSpec {
    fun signIn(request: SignInRequest, response: HttpServletResponse): BasicResponse
}
