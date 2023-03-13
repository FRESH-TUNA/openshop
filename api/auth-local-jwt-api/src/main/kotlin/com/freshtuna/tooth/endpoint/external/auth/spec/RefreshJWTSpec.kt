package com.freshtuna.tooth.endpoint.external.auth.spec

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.endpoint.external.auth.request.RefreshJWTRequest
import jakarta.servlet.http.HttpServletResponse

interface RefreshJWTSpec {

    fun refresh(request: RefreshJWTRequest, response: HttpServletResponse): BasicResponse
}
