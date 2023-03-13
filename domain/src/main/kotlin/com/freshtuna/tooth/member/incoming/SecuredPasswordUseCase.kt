package com.freshtuna.tooth.member.incoming

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.EncryptedPassword

interface SecuredPasswordUseCase {
    fun generate(password: Password): EncryptedPassword

    fun matched(givenPW: Password, storedPW: EncryptedPassword): Boolean
}
