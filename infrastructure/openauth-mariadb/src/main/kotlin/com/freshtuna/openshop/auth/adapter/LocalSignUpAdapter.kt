package com.freshtuna.openshop.auth.adapter

import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.SecuredPassword
import com.freshtuna.openshop.member.entity.MariaDBLocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import org.springframework.stereotype.Component

@Component
class LocalSignUpAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalSignUpPort {

    override fun signUp(localMember: LocalMember, securedPassword: SecuredPassword): LocalMember {
        val newMember = MariaDBLocalMember.of(localMember, securedPassword)

        localMemberRepository.save(newMember)

        return newMember.toLocalMember()
    }
}
