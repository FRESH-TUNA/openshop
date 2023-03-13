package com.freshtuna.tooth.endpoint.external

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.member.incoming.ChangePasswordUseCase
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.endpoint.external.request.ChangePasswordRequest

import com.freshtuna.tooth.endpoint.external.spec.ChangePasswordSpec
import com.freshtuna.tooth.member.Password

import com.freshtuna.tooth.security.userDetail.ToothUserDetailManager
import com.freshtuna.tooth.id.PublicId

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangePasswordController(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val userDetailManager: ToothUserDetailManager
) : ChangePasswordSpec {

    @PostMapping(Url.EXTERNAL.CHANGE_PASSWORD)
    override fun changePassword(
        @RequestBody request: ChangePasswordRequest
    ): BasicResponse {

        changePasswordUseCase.changePassword(
            PublicId( userDetailManager.get().username),
            Password(request.curPassword),
            Password(request.newPassword))

        return MessageResponse.OK
    }
}
