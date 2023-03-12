package com.freshtuna.openshop.auth

import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.SecuredPassword
import com.freshtuna.openshop.auth.command.SignInCommand
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase

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

    private val memberSignInService = JWTSignInComposite(
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
        val localId = "localId"
        val password = Password("password")
        val securedPassword = SecuredPassword("isSecured!")
        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId)

        /**
         * when
         */
        every { memberSearchPort.findLocalMember(localId) } returns member
        every { memberSearchPort.findSavedPasswordByLocalMember(member) } returns securedPassword
        every { securedPasswordUseCase.matched(password, securedPassword) } returns true
        every { jwtUseCase.generateAccessToken(member) } returns JWT.accessOf("accessToken!")
        every { jwtUseCase.generateRefreshToken(member) } returns JWT.refreshOf("refreshToken")

        /**
         * then
         */
        assertEquals(memberSignInService.signIn(SignInCommand(localId, password)).member.publicId, id)
    }
}
