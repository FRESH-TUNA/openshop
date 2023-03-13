package com.freshtuna.tooth.auth.result

import com.freshtuna.tooth.jwt.JWT
import com.freshtuna.tooth.member.Member

class SignInJWTResult(
    member: Member,
    val accessToken: JWT,
    val refreshToken: JWT,
) : SignInResult(member)
