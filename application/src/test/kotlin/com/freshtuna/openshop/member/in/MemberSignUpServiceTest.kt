package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.Role
import com.freshtuna.openshop.member.out.MemberSearchPort
import com.freshtuna.openshop.member.out.MemberSignUpPort
import com.freshtuna.openshop.member.out.MemberUpdatePort
import io.mockk.every

import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class MemberSignUpServiceTest {

    private val memberSignUpPort: MemberSignUpPort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val memberUpdatePort: MemberUpdatePort = mockk()

    private val memberSignUpService = MemberSignUpService(
        memberSignUpPort,
        memberSearchPort,
        memberUpdatePort
    )

    @Test
    @DisplayName("이미 가입된 아이디로는 로컬 회원 가입이 불가하다.")
    fun signupFailWhenSameLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */
        // 고유 식별자
        val id = "식별자"

        // 개인정보 (실명)
        val name = "김동원"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(id, name, nickname, roles, localId, password)

        /**
         * given
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        every { memberSignUpPort.signUp(any<LocalMember>()) } returns true
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns true


        /**
         * when, then
         * 로컬멤버 생성 테스트
         */
        assertThrows<RuntimeException> { memberSignUpService.signUpLocalMember(member) }
    }

    @Test
    @DisplayName("새로운 아이디라면 로컬 회원 가입이 가능하다.")
    fun signupSuccessWhenNewLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */
        // 고유 식별자
        val id = "식별자"

        // 개인정보 (실명)
        val name = "김동원"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(id, name, nickname, roles, localId, password)

        /**
         * given
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        every { memberSignUpPort.signUp(any<LocalMember>()) } returns false
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns false

        /**
         * when, then
         * 로컬멤버 생성 테스트
         */
        assertDoesNotThrow { memberSignUpService.signUpLocalMember(member) }
    }
}
