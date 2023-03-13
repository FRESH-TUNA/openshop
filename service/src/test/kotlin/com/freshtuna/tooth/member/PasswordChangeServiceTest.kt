package com.freshtuna.tooth.member

import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId
import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import com.freshtuna.tooth.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.tooth.member.outgoing.MemberSearchPort
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordChangeServiceTest {

    private val localMemberUpdatePort: LocalMemberUpdatePort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val passwordChangeService = PasswordChangeService(
        localMemberUpdatePort,
        memberSearchPort,
        securedPasswordUseCase
    )

    @Test
    @DisplayName("패스워드 변경 성공 테스트")
    fun changePassword() {
        /**
         * given
         */
        val localId = LocalId("localId")
        val curPassword = Password("password")
        val newPassword = Password("1aB!1aB!1aB!1aB!1aB!")

        val curEncryptedPassword = EncryptedPassword("password")
        val newEncryptedPassword = EncryptedPassword("password")

        val id = PublicId("id")
        val member = LocalMember(id, Lists.emptyList(), localId, curEncryptedPassword)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curEncryptedPassword) } returns true
        every { securedPasswordUseCase.generate(curPassword) } returns curEncryptedPassword
        every { securedPasswordUseCase.generate(newPassword) } returns newEncryptedPassword

        every { memberSearchPort.findLocalMember(id) } returns member
        every { localMemberUpdatePort.changePassword(member, newEncryptedPassword) } returns Unit



        /**
         * then
         */
        assertDoesNotThrow { passwordChangeService.changePassword(id, curPassword, newPassword) }
    }

    @Test
    @DisplayName("패스워드 룰 미준수 실패 테스트")
    fun doNotBreakPasswordRule() {
        /**
         * given
         */
        val localId = LocalId("localId")
        val curPassword = Password("password")
        val newPassword = Password("hmm")

        val curEncryptedPassword = EncryptedPassword("password")
        val newEncryptedPassword = EncryptedPassword("password")

        val id = PublicId("id")
        val member = LocalMember(id, Lists.emptyList(), localId, curEncryptedPassword)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curEncryptedPassword) } returns true
        every { securedPasswordUseCase.generate(curPassword) } returns curEncryptedPassword
        every { securedPasswordUseCase.generate(newPassword) } returns newEncryptedPassword

        every { memberSearchPort.findLocalMember(id) } returns member
        every { localMemberUpdatePort.changePassword(member, newEncryptedPassword) } returns Unit

        /**
         * then
         */
        assertThrows<ToothException> { passwordChangeService.changePassword(id, curPassword, newPassword) }
    }
}
