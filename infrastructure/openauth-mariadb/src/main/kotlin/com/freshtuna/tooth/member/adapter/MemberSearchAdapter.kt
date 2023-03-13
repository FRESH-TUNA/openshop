package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.outgoing.MemberSearchPort
import com.freshtuna.tooth.member.constant.Provider
import com.freshtuna.tooth.id.LocalId
import com.freshtuna.tooth.id.PublicId
import org.springframework.stereotype.Component
import java.util.*

@Component
class MemberSearchAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : MemberSearchPort {

    override fun existsLocalMember(localId: LocalId): Boolean {
        return localMemberRepository.existsByLocalId(localId.toString())
    }

    override fun findLocalMember(localId: LocalId): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId.toString())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findLocalMember(id: PublicId): LocalMember {
        val optionalOfLocalMember = localMemberRepository.findByPublicId(UUID.fromString(id.toString()))

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun existsOAuthMember(oauthId: String, provider: Provider): Boolean {
        TODO("Not yet implemented")
    }

    override fun findLocalMemberByIdAndPassword(localId: String, password: Password): LocalMember {
        TODO("Not yet implemented")
    }
}
