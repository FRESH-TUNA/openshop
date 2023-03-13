package com.freshtuna.tooth.auth

import com.freshtuna.tooth.jwt.JWT
import com.freshtuna.tooth.member.outgoing.MemberSearchPort
import com.freshtuna.tooth.jwt.incoming.JWTUseCase
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.EncryptedPassword
import com.freshtuna.tooth.auth.command.LocalSignInCommand
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId
import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class LocalSignInJWTSignUpCompositeTest {

    private val memberSearchPort: MemberSearchPort = mockk()
    private val jwtUseCase: JWTUseCase = mockk()
    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val memberSignInService = LocalSignInComposite(
        memberSearchPort,
        jwtUseCase,
        securedPasswordUseCase
    )

    @Test
    @DisplayName("유저ID와 패스워드로 로그인을 시도한다")
    fun signInLocalMemberSuccess() {
        /**
         * given
         * 아이디, 비밀번호, 멤버의 데이터베이스 상의 id
         * 로그인 성공시 반환할 유저객체
         * 테스트할 서비스 객체
         */
        val localId = LocalId("localId")
        val password = Password("password")
        val encryptedPassword = EncryptedPassword("isSecured!")
        val id = PublicId("id")
        val member = LocalMember(id, Lists.emptyList(), localId, encryptedPassword)

        /**
         * when
         */
        every { memberSearchPort.findLocalMember(localId) } returns member
        every { securedPasswordUseCase.matched(password, encryptedPassword) } returns true
        every { jwtUseCase.generateAccessToken(member) } returns JWT.accessOf("accessToken!")
        every { jwtUseCase.generateRefreshToken(member) } returns JWT.refreshOf("refreshToken")

        /**
         * then
         */
        assertEquals(memberSignInService.signIn(LocalSignInCommand(localId, password)).member.publicId, id)
    }
}
