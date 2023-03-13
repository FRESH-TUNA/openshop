package com.freshtuna.tooth.auth.incoming

import com.freshtuna.tooth.auth.result.SignInResult
import com.freshtuna.tooth.auth.command.LocalSignInCommand

interface LocalSignInUseCase {

    /**
     * 로컬id와 패스워드로 로그인을 시도 한다.
     * 성공하면 로그인에 성공하면 해당 멤버를 반환한다.
     */
    fun signIn(command: LocalSignInCommand): SignInResult
}
