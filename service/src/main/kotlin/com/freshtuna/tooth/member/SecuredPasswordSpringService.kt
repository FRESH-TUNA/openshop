package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecuredPasswordSpringService(
    private val passwordEncoder: PasswordEncoder
) : SecuredPasswordUseCase {

    override fun generate(password: Password): EncryptedPassword {
        return EncryptedPassword(passwordEncoder.encode(password.passwordString))
    }

    override fun matched(givenPW: Password, storedPW: EncryptedPassword): Boolean {
        return passwordEncoder.matches(givenPW.passwordString, storedPW.passwordString)
    }
}
