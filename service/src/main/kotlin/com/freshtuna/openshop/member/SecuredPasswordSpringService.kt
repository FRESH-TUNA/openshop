package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import org.springframework.security.crypto.password.PasswordEncoder

class SecuredPasswordSpringService(
    private val passwordEncoder: PasswordEncoder
) : SecuredPasswordUseCase {

    override fun generate(password: Password): SecuredPassword {
        return SecuredPassword(passwordEncoder.encode(password.passwordString))
    }
}
