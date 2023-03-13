package com.freshtuna.tooth.endpoint.external.auth.spec

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.endpoint.external.auth.request.SignUpRequest
import jakarta.servlet.http.HttpServletResponse

interface LocalMemberSignUpSpec {
    fun signUp(request: SignUpRequest, response: HttpServletResponse): BasicResponse
}
