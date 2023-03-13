package com.freshtuna.tooth.endpoint.external.auth.request

import com.freshtuna.tooth.auth.command.LocalSignUpCommand
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.id.LocalId

class SignUpRequest(
    val id: String,
    val nickname: String?,
    val password: String,
    private val repeatPassword: String
) {
    fun toCommand(): LocalSignUpCommand
        = LocalSignUpCommand(LocalId(id), Password(password), Password(repeatPassword))
}
