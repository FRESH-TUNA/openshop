package com.freshtuna.tooth.auth.command

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.id.LocalId

class LocalSignInCommand(
    val localId: LocalId,
    val password: Password
)
