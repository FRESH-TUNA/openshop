package com.freshtuna.tooth.auth

import com.freshtuna.tooth.member.outgoing.MemberSearchPort
import com.freshtuna.tooth.auth.incoming.LocalSignInUseCase
import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.auth.result.SignInResult
import com.freshtuna.tooth.jwt.incoming.JWTUseCase
import com.freshtuna.tooth.auth.command.LocalSignInCommand
import com.freshtuna.tooth.auth.result.SignInJWTResult
import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocalSignInComposite(
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : LocalSignInUseCase {
    override fun signIn(command: LocalSignInCommand): SignInResult {

        val member = memberSearchPort.findLocalMember(command.localId)

        if(!securedPasswordUseCase.matched(command.password, member.password))
            Oh.localAuthenticationFail()

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return SignInJWTResult(member, accessToken, refreshToken)
    }
}
