package com.freshtuna.tooth.endpoint.external.auth.request

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.auth.command.LocalSignInCommand
import com.freshtuna.tooth.id.LocalId

class SignInRequest(
    val id: String,
    val password: String
) {
    fun toCommand() = LocalSignInCommand(LocalId(id), Password(password))
}
