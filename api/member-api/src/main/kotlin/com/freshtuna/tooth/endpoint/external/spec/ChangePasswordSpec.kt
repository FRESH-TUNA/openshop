package com.freshtuna.tooth.endpoint.external.spec

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.endpoint.external.request.ChangePasswordRequest

interface ChangePasswordSpec {

    fun changePassword(
        request: ChangePasswordRequest
    ) : BasicResponse
}
