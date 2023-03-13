package com.freshtuna.tooth.auth.incoming

import com.freshtuna.tooth.auth.result.SignInResult
import com.freshtuna.tooth.auth.command.LocalSignUpCommand

interface LocalSignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUp(command: LocalSignUpCommand): SignInResult
}
