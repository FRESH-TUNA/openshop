package com.freshtuna.tooth.auth.adapter

import com.freshtuna.tooth.auth.command.LocalSignUpCommand
import com.freshtuna.tooth.auth.outgoing.LocalSignUpPort
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.EncryptedPassword
import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import org.springframework.stereotype.Component

@Component
class LocalSignUpAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalSignUpPort {

    override fun signUp(command: LocalSignUpCommand, encryptedPassword: EncryptedPassword): LocalMember {
        val newMember = MariaDBLocalMember.of(command, encryptedPassword)

        localMemberRepository.save(newMember)

        return newMember.toLocalMember()
    }
}
