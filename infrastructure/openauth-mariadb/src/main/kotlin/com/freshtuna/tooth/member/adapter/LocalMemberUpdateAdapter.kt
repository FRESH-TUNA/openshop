package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.EncryptedPassword
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import com.freshtuna.tooth.member.outgoing.LocalMemberUpdatePort
import org.springframework.stereotype.Component
import java.util.*

@Component
class LocalMemberUpdateAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberUpdatePort {

    override fun changePassword(member: LocalMember, newPassword: EncryptedPassword) {
        val optionalOfLocalMember = localMemberRepository
            .findByPublicId(UUID.fromString(member.publicId.toString()))

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        optionalOfLocalMember.get().changePassword(newPassword)
    }
}
