package com.freshtuna.openshop.endpoint.external.auth.request

import com.freshtuna.openshop.auth.command.LocalSignUpCommand
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.id.LocalId

class SignUpRequest(
    val id: String,
    val nickname: String?,
    val password: String,
    val repeatPassword: String
) {
    fun toCommand(): LocalSignUpCommand
        = LocalSignUpCommand(LocalId(id), Password(password), Password(password))
}
